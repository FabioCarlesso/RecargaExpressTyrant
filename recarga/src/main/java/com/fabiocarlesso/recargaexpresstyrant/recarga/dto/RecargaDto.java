package com.fabiocarlesso.recargaexpresstyrant.recarga.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecargaDto {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(max=100)
    private String numeroCelular;
    @Positive
    private BigDecimal valorRecarga;
    private String metodoPagamento;
    @NotNull @Size(max=100)
    private String usuario;
    private String operadora;
}
