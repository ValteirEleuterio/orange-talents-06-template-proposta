package br.com.zupacademy.valteir.proposta.cartoesapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "${cartoes.host}")
public interface CartoesApi {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes")
    CartaoResponse cartoes(@RequestParam("idProposta") String idProposta);
}
