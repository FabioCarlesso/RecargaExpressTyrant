package com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecargaValidarRequest {
    @NotNull(message = "Número de celular deve ser inserido")
    @Positive(message = "Número de celular deve ser um numero positivo maior que zero")
    @DecimalMin(value = "1000", message = "O valor deve ser igual ou maior que 1000")
    @DecimalMax(value = "1000000000000000000", message = "O valor deve ser igual ou menor que 1000000000000000000")
    private Long numeroCelular;
}
