package com.fabiocarlesso.recargaexpresstyrant.recarga.integrador;

import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargapagamento.RecargaPagamentoRequest;
import com.fabiocarlesso.recargaexpresstyrant.recarga.dto.recargapagamento.RecargaPagamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "recarga-pagamento-ms", url = "https://b1b79955405744eba9477d89ce36e989.api.mockbin.io/")
public interface RecargaPagamentoIntegration {
    @PostMapping(value = "", consumes = "application/json")
    RecargaPagamentoResponse postPagamentoRecarga(@RequestBody RecargaPagamentoRequest request);
}
