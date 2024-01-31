package com.fabiocarlesso.recargaexpresstyrant.recarga.service;

import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.RecargaDto;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador.RecargaValidarRequest;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador.RecargaValidarResponse;
import com.fabiocarlesso.recargaexpresstyrant.recarga.integrador.RecargaValidadorIntegration;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Recarga;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Status;
import com.fabiocarlesso.recargaexpresstyrant.recarga.repository.RecargaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecargaService {
    private final RecargaRepository recargaRepository;
    private final ModelMapper modelMapper;
    private final RecargaValidadorIntegration recargaValidadorIntegration;
    public RecargaDto solicitarRecarga(RecargaDto dto){
        Recarga recarga = modelMapper.map(dto, Recarga.class);
        recarga.setDataHoraSolicitacao(LocalDateTime.now());
        recarga.setStatus(Status.SOLICITADO);
        Recarga salvo = recargaRepository.save(recarga);
        //1. Validar recargas
        RecargaValidarResponse validarResponse = recargaValidadorIntegration.postValidarRecarga(
                new RecargaValidarRequest(recarga.getNumeroCelular()));
        log.info("Valido? {}", validarResponse);
        return modelMapper.map(salvo, RecargaDto.class);
    }

    public RecargaDto obterPorId(Long id) {
        Recarga recarga = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(recarga, RecargaDto.class);
    }

    //2. Status pagamentos (EM_PAGAMENTO, NAO_AUTORIZADO, PAGO)
    public RecargaDto aprovaPagamentoRecarga(Long id) {
        Recarga recarga = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        recarga.setStatus(Status.PAGO);
        Recarga salvo = recargaRepository.save(recarga);
        return modelMapper.map(salvo, RecargaDto.class);
    }


    //3. Realizar recarga (NAO_REALIZADO, REALIZADO
}
