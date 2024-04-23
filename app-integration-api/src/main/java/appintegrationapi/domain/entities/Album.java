package appintegrationapi.domain.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ALBUM")
@Getter
@Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ID_SPOTIFY")
    private String idSpotify;

    @Column(name = "ARTIST_NAME")
    private String artistName;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "VALUE")
    private Double value;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

}

