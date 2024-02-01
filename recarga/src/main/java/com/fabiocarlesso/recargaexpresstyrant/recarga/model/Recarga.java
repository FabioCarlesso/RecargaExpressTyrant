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
    private LocalDateTime dataHoraPagamento;
    private LocalDateTime dataHoraRealizado;
    @NotNull @Enumerated(EnumType.STRING)
    private Status status;
}
