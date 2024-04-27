package appintegrationapi.domain.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "albums")
@Getter
@Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_spotify")
    private String idSpotify;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "value")
    private Double value;

    @Column(name = "id_users")
    private String userId;

    public Album(String name, String idSpotify, String artistName, String imageUrl, Double value, String userId) {
        this.name = name;
        this.idSpotify = idSpotify;
        this.artistName = artistName;
        this.imageUrl = imageUrl;
        this.value = value;
        this.userId = userId;
    }
}

