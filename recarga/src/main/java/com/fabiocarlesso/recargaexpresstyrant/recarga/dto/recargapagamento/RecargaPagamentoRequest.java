package com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargapagamento;

import com.fabiocarlesso.recargaexpresstyrant.recarga.model.MetodoPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RecargaPagamentoRequest {
    @Positive(message = "Valor da recarga deve ser um numero positivo maior que zero")
    private BigDecimal valorRecarga;
    @NotNull(message = "Metodo de pagamento deve ser inserido")
    private MetodoPagamento metodoPagamento;
}
