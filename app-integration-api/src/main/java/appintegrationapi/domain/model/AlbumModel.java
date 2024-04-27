package appintegrationapi.domain.model;

import lombok.Data;
import se.michaelthelin.spotify.enums.AlbumType;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;

@Data
public class AlbumModel {
    private AlbumType albumType;
    private ArtistSimplified[] artists;
    private ExternalUrl externalUrl;
    private String id;
    private Image[] images;
    private String name;
    private String releaseDate;
    private ModelObjectType type;
    private Double value;
}


