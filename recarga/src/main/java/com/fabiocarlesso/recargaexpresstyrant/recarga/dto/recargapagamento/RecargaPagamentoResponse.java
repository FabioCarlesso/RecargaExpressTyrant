package com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargapagamento;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecargaPagamentoResponse {
    private Boolean isRealizado;
    private String message;
}
