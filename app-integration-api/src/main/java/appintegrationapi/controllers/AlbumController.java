package appintegrationapi.controllers;

import appintegrationapi.domain.dtos.AlbumDTO;
import appintegrationapi.domain.entities.Album;
import appintegrationapi.domain.model.AlbumModel;
import appintegrationapi.domain.services.AlbumService;
import jakarta.transaction.Transactional;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

//List<AlbumModel>
    @GetMapping("/all")
    public ResponseEntity<?> getAlbums(@RequestParam("search") String search) throws IOException, ParseException, SpotifyWebApiException {
        return ResponseEntity.ok(albumService.getAlbums(search));
    }

    @Transactional
    @PostMapping("/sale")
    public ResponseEntity<Album> saveAlbum(@RequestBody AlbumDTO album) {
        return ResponseEntity.ok(albumService.saveAlbum(album));
    }


}
