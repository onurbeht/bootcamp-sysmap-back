package br.com.sysmap.bootcamp.domain.dtos;

import java.util.List;

public record AlbumDTOResponse(
                String name,
                String idSpotify,
                List<String> artistsName,
                List<String> images,
                Double value

) {
}
