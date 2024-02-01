package com.fabiocarlesso.recargaexpresstyrant.recarga.controller;

import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.RecargaDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.pagamento.PagamentoRecargaResponseDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.solicitar.SolicitarRecargaRequestDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.solicitar.SolicitarRecargaResponseDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.service.RecargaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/recargas")
@RequiredArgsConstructor
public class RecargaController {
    private final RecargaService service;
    @PostMapping
    @Transactional
    public ResponseEntity<SolicitarRecargaResponseDto> solicitaRecarga(@RequestBody @Valid SolicitarRecargaRequestDto dto, UriComponentsBuilder uriBuilder) {
        SolicitarRecargaResponseDto recargaSolicitada = service.solicitarRecarga(dto);
        URI endereco = uriBuilder.path("/api/recargas/{id}").buildAndExpand(recargaSolicitada.getId()).toUri();
        return ResponseEntity.created(endereco).body(recargaSolicitada);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecargaDto> buscarPorId(@PathVariable @NotNull Long id) {
        RecargaDto dto = service.obterPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/pago")
    @Transactional
    public ResponseEntity<PagamentoRecargaResponseDto> aprovaPagamento(@PathVariable @NotNull Long id) {
        PagamentoRecargaResponseDto recargaPaga = service.aprovaPagamentoRecarga(id);
        return ResponseEntity.ok(recargaPaga);
    }

    @PutMapping("/{id}/realizar")
    @Transactional
    public ResponseEntity<RecargaDto> realizarRecarga(@PathVariable @NotNull Long id) {
        RecargaDto dto = service.realizaRecarga(id);
        return ResponseEntity.ok(dto);
    }
}
