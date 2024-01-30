package com.fabiocarlesso.recargaexpresstyrant.recarga.integrador;

import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador.RecargaValidarRequest;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargavalidador.RecargaValidarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "recarga-validar-ms", url = "https://7363efd7f91042abb67d7184f97e3ff7.api.mockbin.io/")
public interface RecargaValidadorIntegration {
    @PostMapping(value = "", consumes = "application/json")
    RecargaValidarResponse postValidarRecarga(@RequestBody RecargaValidarRequest request);
}
