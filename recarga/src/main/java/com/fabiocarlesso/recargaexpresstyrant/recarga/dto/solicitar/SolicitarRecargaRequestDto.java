package com.fabiocarlesso.recargaexpresstyrant.recarga.dto.solicitar;

import com.fabiocarlesso.recargaexpresstyrant.recarga.model.MetodoPagamento;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Operadora;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitarRecargaRequestDto {
    @NotNull(message = "Número de celular deve ser inserido")
    @Positive(message = "Número de celular deve ser um numero positivo maior que zero")
    @DecimalMin(value = "1000", message = "O valor deve ser igual ou maior que 1000")
    @DecimalMax(value = "1000000000000000000", message = "O valor deve ser igual ou menor que 1000000000000000000")
    private Long numeroCelular;
    @Positive(message = "Valor da recarga deve ser um numero positivo maior que zero")
    private BigDecimal valorRecarga;
    @NotNull(message = "Metodo de pagamento deve ser inserido")
    private MetodoPagamento metodoPagamento;
    @NotEmpty(message = "Usuario não pode ser em branco")
    @Size(min = 3, max = 100, message = "Usuario deve ter entre 3 e 100 caracteres")
    private String usuario;
    @NotNull(message = "Operadora deve ser inserido")
    private Operadora operadora;
}
