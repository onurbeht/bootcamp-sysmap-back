package br.com.sysmap.bootcamp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "albums")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_spotify")
    private String idSpotify;

    @Column(name = "artist")
    private List<String> artistName = new ArrayList<>();

    @Column(name = "image_url")
    private List<String> imageUrl = new ArrayList<>();

    @Column(name = "value")
    private Double value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Album(String name, String idSpotify, String[] artistName, String[] imageUrl, Double value, User user) {
        this.name = name;
        this.idSpotify = idSpotify;
        this.artistName.add(Arrays.toString(artistName));
        this.imageUrl.add(Arrays.toString(imageUrl));
        this.value = value;
        this.user = user;
    }

    
}

