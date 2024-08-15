package br.com.sysmap.bootcamp.domain.dtos.wallet;

public record WalletClientDTO(
        Double balance,
        Integer points,
        String owner) {

}
