package br.com.sysmap.bootcamp.controllers;

import br.com.sysmap.bootcamp.domain.dtos.AlbumDTO;
import br.com.sysmap.bootcamp.domain.entities.Album;
import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import br.com.sysmap.bootcamp.domain.services.AlbumService;
import jakarta.transaction.Transactional;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAlbums(@RequestParam("search") String search)
            throws IOException, ParseException, SpotifyWebApiException {
        return ResponseEntity.ok(albumService.getAlbums(search));
    }

    @GetMapping("/new")
    public ResponseEntity<List<AlbumModel>> getNew() throws IOException, ParseException, SpotifyWebApiException {
        return ResponseEntity.ok(albumService.getNew());
    }

    @Transactional
    @PostMapping("/sale")
    public ResponseEntity<?> saveAlbum(@RequestBody AlbumDTO album,
            @RequestHeader("Authorization") String authorizationHeader) {

        var albumResponse = albumService.saveAlbum(album, authorizationHeader);

        if (albumResponse == null)
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
                    .body("Insuficient founds! Check your wallet balance.");

        return ResponseEntity.ok(albumResponse);
    }

}
