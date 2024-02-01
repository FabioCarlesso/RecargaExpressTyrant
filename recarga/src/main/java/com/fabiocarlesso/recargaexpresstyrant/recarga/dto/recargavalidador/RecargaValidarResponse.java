package com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecargaValidarResponse {
    private Boolean isValido;
    private String message;
}
