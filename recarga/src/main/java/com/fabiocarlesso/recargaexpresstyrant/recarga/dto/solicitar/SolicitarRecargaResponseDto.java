package com.fabiocarlesso.recargaexpresstyrant.recarga.dto.solicitar;

import com.fabiocarlesso.recargaexpresstyrant.recarga.model.MetodoPagamento;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Operadora;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Status;
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
public class SolicitarRecargaResponseDto {
    private Long id;
    private Long numeroCelular;
    private BigDecimal valorRecarga;
    private MetodoPagamento metodoPagamento;
    private String usuario;
    private Operadora operadora;
    private LocalDateTime dataHoraSolicitacao;
    private Status status;
}
