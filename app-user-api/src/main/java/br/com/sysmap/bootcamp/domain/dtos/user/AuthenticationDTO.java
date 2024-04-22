package br.com.sysmap.bootcamp.domain.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank String email, @NotBlank String password

) {
}
