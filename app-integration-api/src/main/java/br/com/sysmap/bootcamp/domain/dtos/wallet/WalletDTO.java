package br.com.sysmap.bootcamp.domain.dtos.wallet;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WalletDTO implements Serializable {

    private String email;
    private Double value;

    public WalletDTO(String email, Double value) {
        this.email = email;
        this.value = value;
    }

}
