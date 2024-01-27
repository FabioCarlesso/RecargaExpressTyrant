package com.fabiocarlesso.recargaexpresstyrant.recarga.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotNull @NotEmpty @Size(max=100)
    private String numeroCelular;
    @Positive
    private BigDecimal valorRecarga;
    private String metodoPagamento;
    @NotNull @Size(max=100)
    private String usuario;
    private String operadora;
    @NotNull
    private LocalDateTime dataHoraSolicitacao;
    @NotNull @Enumerated(EnumType.STRING)
    private Status status;
}
