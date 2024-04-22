package br.com.sysmap.bootcamp.domain.dtos.user;

import br.com.sysmap.bootcamp.domain.entities.Wallet;

public record ResponseOneUserDTO(String id, String name, String email, Wallet wallet)
{
}
