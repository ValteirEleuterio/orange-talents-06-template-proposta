package br.com.zupacademy.valteir.proposta.outrossistemas.analiseapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "analise-solicitante", url = "${analise.host}")
public interface AnaliseApi {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
    AnaliseResponse analise(AnaliseRequest analiseRequest);
}
