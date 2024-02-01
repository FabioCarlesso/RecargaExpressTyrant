package com.fabiocarlesso.recargaexpresstyrant.recarga.service;

import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.RecargaDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.pagamento.PagamentoRecargaResponseDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.realizar.RealizaRecargaResponseDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador.RecargaValidarRequest;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador.RecargaValidarResponse;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.solicitar.SolicitarRecargaRequestDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.solicitar.SolicitarRecargaResponseDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.exception.StatusNotFoundExeception;
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
    public PagamentoRecargaResponseDto aprovaPagamentoRecarga(Long id) {
        Recarga recarga = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!recarga.getStatus().equals(Status.SOLICITADO))
            throw new StatusNotFoundExeception("Solicitação não encontrada!");
        recarga.setDataHoraPagamento(LocalDateTime.now());
        recarga.setStatus(Status.PAGO);
        Recarga salvo = recargaRepository.save(recarga);
        return modelMapper.map(salvo, PagamentoRecargaResponseDto.class);
    }

    //3. Realizar recarga (NAO_REALIZADO, REALIZADO
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public RealizaRecargaResponseDto realizaRecarga(Long id) {
        Recarga recarga = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!recarga.getStatus().equals(Status.PAGO))
            throw new StatusNotFoundExeception("Verificar solicitação de recarga!");
        //Validar endpoint Operadora
        recarga.setDataHoraRealizado(LocalDateTime.now());
        recarga.setStatus(Status.REALIZADO);
        Recarga salvo = recargaRepository.save(recarga);
        return modelMapper.map(salvo, RealizaRecargaResponseDto.class);
    }
}
