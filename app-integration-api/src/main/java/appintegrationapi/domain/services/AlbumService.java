package appintegrationapi.domain.services;

import appintegrationapi.domain.dtos.AlbumDTO;
import appintegrationapi.domain.dtos.WalletDTO;
import appintegrationapi.domain.entities.Album;
import appintegrationapi.domain.model.AlbumModel;
import appintegrationapi.domain.repositories.AlbumRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class AlbumService {

    private final Queue queue;
    private final RabbitTemplate template;
    private final SpotifyApiIntegration spotifyApi;
    private final AlbumRepository albumRepository;
    private final UserService userService;

    public List<AlbumModel> getAlbums(String search) throws IOException, ParseException, SpotifyWebApiException {

        return spotifyApi.getAlbums(search);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Album saveAlbum(AlbumDTO albumData) {

        var currentUser = userService.getUser();

        Album album = new Album(albumData.name(), albumData.idSpotify(), albumData.artistName(), albumData.imageUrl(), albumData.value(), currentUser.getId());

        Album albumSaved = albumRepository.save(album);

        WalletDTO walletDto = new WalletDTO(currentUser.getEmail(), albumSaved.getValue());
        template.convertAndSend(queue.getName(), walletDto);

        return albumSaved;
    }


}