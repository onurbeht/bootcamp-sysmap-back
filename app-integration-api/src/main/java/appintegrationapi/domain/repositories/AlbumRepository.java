package appintegrationapi.domain.repositories;

import appintegrationapi.domain.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {


}

