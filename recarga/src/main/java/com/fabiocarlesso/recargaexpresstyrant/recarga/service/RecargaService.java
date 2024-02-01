package com.fabiocarlesso.recargaexpresstyrant.recarga.service;

import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.RecargaDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador.RecargaValidarRequest;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador.RecargaValidarResponse;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.solicitar.SolicitarRecargaRequestDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.solicitar.SolicitarRecargaResponseDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.integrador.RecargaValidadorIntegration;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Recarga;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Status;
import com.fabiocarlesso.recargaexpresstyrant.recarga.repository.RecargaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecargaService {
    private final RecargaRepository recargaRepository;
    private final ModelMapper modelMapper;
    private final RecargaValidadorIntegration recargaValidadorIntegration;
    public SolicitarRecargaResponseDto solicitarRecarga(SolicitarRecargaRequestDto dto){
        Recarga recarga = modelMapper.map(dto, Recarga.class);
        recarga.setDataHoraSolicitacao(LocalDateTime.now());
        recarga.setStatus(Status.SOLICITADO);
        Recarga salvo = recargaRepository.save(recarga);
        //1. Validar recargas
        RecargaValidarResponse validarResponse = recargaValidadorIntegration.postValidarRecarga(
                new RecargaValidarRequest(recarga.getNumeroCelular()));
        log.info("Valido? {}", validarResponse);
        return modelMapper.map(salvo, SolicitarRecargaResponseDto.class);
    }

    public RecargaDto obterPorId(Long id) {
        Recarga recarga = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(recarga, RecargaDto.class);
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void aprovaPagamentoRecarga(Long id) {
        //Só pode pagar algo que foi solicitado
        Recarga recarga = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        recargaRepository.atualizaRecargaStatus(recarga.getId(), Status.PAGO);
    }

    //3. Realizar recarga (NAO_REALIZADO, REALIZADO
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public RecargaDto realizaRecarga(Long id) {
        Recarga recarga = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        //Só pode realizar algo que foi pago
        //Validar endpoint Operadora
        recargaRepository.atualizaRecargaStatus(recarga.getId(), Status.REALIZADO);
        Recarga atualizado = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(atualizado, RecargaDto.class);
    }
}
