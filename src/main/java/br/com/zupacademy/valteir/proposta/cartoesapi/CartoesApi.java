package br.com.zupacademy.valteir.proposta.cartoesapi;

import br.com.zupacademy.valteir.proposta.criaravisoviagem.ViagemRequest;
import br.com.zupacademy.valteir.proposta.criarcarteira.CarteiraRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartoes", url = "${cartoes.host}")
public interface CartoesApi {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes")
    CartaoResponse cartoes(@RequestParam("idProposta") String idProposta);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
    BloqueioResponse bloqueios(@PathVariable("id") String idCartao, BloqueioRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/avisos")
    void avisos(@PathVariable("id") String idCartao, ViagemRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/carteiras")
    void carteiras(@PathVariable("id") String idCartao, CarteiraRequest request);
}
