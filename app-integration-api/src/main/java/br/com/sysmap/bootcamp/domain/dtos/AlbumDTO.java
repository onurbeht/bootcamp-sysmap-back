package br.com.sysmap.bootcamp.domain.dtos;

public record AlbumDTO(
        String name,
        String idSpotify,
        String[] artistsName,
        String[] images,
        Double value

) {
}
