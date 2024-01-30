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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecargaService {
    private final RecargaRepository recargaRepository;
    private final ModelMapper modelMapper;
    private final RecargaValidadorIntegration recargaValidadorIntegration;
    public RecargaDto solicitarRecarga(RecargaDto dto){
        Recarga recarga = modelMapper.map(dto, Recarga.class);
        recarga.setDataHoraSolicitacao(LocalDateTime.now());
        recarga.setStatus(Status.SOLICITADO);
        Recarga salvo = recargaRepository.save(recarga);
        //Aplicar chain of responsability
        //1. Validar recargas
        RecargaValidarResponse validarResponse = recargaValidadorIntegration.postValidarRecarga(
                new RecargaValidarRequest(recarga.getNumeroCelular()));
        //2. Realizar pagamentos

        //3. Realizar recarga
        //ENVIAR SQS PARA PAGAMENTO
        //ENVIAR SNS PARA AVISAR O CLIENTE
        return modelMapper.map(salvo, RecargaDto.class);
    }

    public RecargaDto obterPorId(Long id) {
        Recarga recarga = recargaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(recarga, RecargaDto.class);
    }
}
