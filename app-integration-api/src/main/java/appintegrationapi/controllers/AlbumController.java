package appintegrationapi.controllers;

import appintegrationapi.domain.model.AlbumModel;
import appintegrationapi.domain.services.SpotifyApiIntegration;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final SpotifyApiIntegration spotifyApi;

    AlbumController(SpotifyApiIntegration spotifyApi) {
        this.spotifyApi = spotifyApi;
    }


    @GetMapping("/all")
    public ResponseEntity<List<AlbumModel>> getAlbums(@RequestParam("search") String search) throws IOException, ParseException, SpotifyWebApiException {
        return ResponseEntity.ok(spotifyApi.getAlbums(search));
    }

}
