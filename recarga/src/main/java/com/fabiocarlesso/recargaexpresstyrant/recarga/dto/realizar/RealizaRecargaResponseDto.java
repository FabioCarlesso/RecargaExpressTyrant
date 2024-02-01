package com.fabiocarlesso.recargaexpresstyrant.recarga.dto.realizar;

import com.fabiocarlesso.recargaexpresstyrant.recarga.model.MetodoPagamento;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Operadora;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealizaRecargaResponseDto {
    private Long id;
    private Long numeroCelular;
    private BigDecimal valorRecarga;
    private MetodoPagamento metodoPagamento;
    private String usuario;
    private Operadora operadora;
    private LocalDateTime dataHoraSolicitacao;
    private LocalDateTime dataHoraPagamento;
    private LocalDateTime dataHoraRealizado;
    private Status status;
}
