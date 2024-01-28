package com.fabiocarlesso.recargaexpresstyrant.recarga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recargas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive @NotNull
    @DecimalMin(value = "1000", message = "O valor deve ser igual ou maior que 1000")
    @DecimalMax(value = "1000000000000000000", message = "O valor deve ser igual ou menor que 1000000000000000000")
    private Long numeroCelular;
    @Positive
    private BigDecimal valorRecarga;
    @NotNull @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    @NotEmpty @Size(min=3,max=100)
    private String usuario;
    @NotNull @Enumerated(EnumType.STRING)
    private Operadora operadora;
    @NotNull
    private LocalDateTime dataHoraSolicitacao;
    @NotNull @Enumerated(EnumType.STRING)
    private Status status;
}
