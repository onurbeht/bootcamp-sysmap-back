package br.com.sysmap.bootcamp.domain.dtos.wallet;

import jakarta.validation.constraints.Positive;

public record RequestDTO(
        @Positive Double value
) {
}
