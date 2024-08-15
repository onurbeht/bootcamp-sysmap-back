package br.com.sysmap.bootcamp.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.sysmap.bootcamp.domain.dtos.wallet.WalletClientDTO;

@FeignClient(name = "wallet-client", url = "http://localhost:8081/api/wallet")
public interface WalletClient {

    @GetMapping
    public WalletClientDTO getWalletInfo(@RequestHeader("Authorization") String token);
}
