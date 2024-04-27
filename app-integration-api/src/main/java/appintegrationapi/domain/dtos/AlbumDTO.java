package appintegrationapi.domain.dtos;

public record AlbumDTO(
        String name,
        String idSpotify,
        String artistName,
        String imageUrl,
        Double value

) {
}
