package com.fabiocarlesso.recargaexpresstyrant.recarga.dto;

import com.fabiocarlesso.recargaexpresstyrant.recarga.model.MetodoPagamento;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Operadora;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecargaDto {
    private Long id;
    @Positive @NotNull
    private Long numeroCelular;
    @Positive
    private BigDecimal valorRecarga;
    @NotNull
    private MetodoPagamento metodoPagamento;
    @NotEmpty
    private String usuario;
    @NotNull
    private Operadora operadora;
    @NotNull
    private LocalDateTime dataHoraSolicitacao;
    private LocalDateTime dataHoraPagamento;
    private LocalDateTime dataHoraRealizado;
    @NotNull
    private Status status;
}
