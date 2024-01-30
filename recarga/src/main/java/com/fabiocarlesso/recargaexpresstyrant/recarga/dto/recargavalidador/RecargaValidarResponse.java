package com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecargaValidarResponse {
    private Boolean isValido;
    private String message;
}
