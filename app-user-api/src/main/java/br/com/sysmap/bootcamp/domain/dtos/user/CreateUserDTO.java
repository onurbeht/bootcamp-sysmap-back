package br.com.sysmap.bootcamp.domain.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password
) {
}
