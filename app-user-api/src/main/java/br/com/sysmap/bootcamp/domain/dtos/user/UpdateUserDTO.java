package br.com.sysmap.bootcamp.domain.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        String username,
        @NotBlank String password
)
{
}
