package br.com.sysmap.bootcamp.domain.services;

import br.com.sysmap.bootcamp.domain.dtos.AlbumDTO;
import br.com.sysmap.bootcamp.domain.dtos.wallet.WalletDTO;
import br.com.sysmap.bootcamp.domain.entities.Album;
import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import br.com.sysmap.bootcamp.domain.repositories.AlbumRepository;
import br.com.sysmap.bootcamp.infra.client.WalletClient;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlbumService {

    private final Queue queue;
    private final RabbitTemplate template;
    private final SpotifyApiIntegration spotifyApi;
    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final WalletClient walletClient;

    public List<AlbumModel> getAlbums(String search) throws IOException, ParseException, SpotifyWebApiException {

        return spotifyApi.getAlbums(search);
    }

    public List<AlbumModel> getNew() throws IOException, ParseException, SpotifyWebApiException {
        return spotifyApi.getNew();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Album saveAlbum(AlbumDTO albumData, String token) {

        var currentUser = userService.getUser();
        var userWallet = walletClient.getWalletInfo(token);

        Album album;

        if (userWallet.balance() < albumData.value()) {
            return album = null;
        }

        album = new Album(albumData.name(), albumData.idSpotify(), albumData.artistsName(), albumData.images(),
                albumData.value(), currentUser);

        System.out.println(album);

        // Album albumSaved = albumRepository.save(album);

        WalletDTO walletDto = new WalletDTO(currentUser.getEmail(), albumData.value());
        // albumSaved.getValue());
        this.template.convertAndSend(queue.getName(), walletDto);

        // return albumSaved;

        return album;
    }

}