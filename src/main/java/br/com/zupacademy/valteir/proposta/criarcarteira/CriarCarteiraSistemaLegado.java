package br.com.zupacademy.valteir.proposta.criarcarteira;

import br.com.zupacademy.valteir.proposta.cartoesapi.CartoesApi;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CriarCarteiraSistemaLegado {

    private CartoesApi cartoesApi;

    public CriarCarteiraSistemaLegado(CartoesApi cartoesApi) {
        this.cartoesApi = cartoesApi;
    }

    public void criar(String numeroCartao, CarteiraRequest request) {
        try {
            cartoesApi.carteiras(numeroCartao, request);
        } catch (FeignException.FeignClientException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Esta carteira já esta cadastrada");
        } catch (FeignException.FeignServerException e) {
            throw new ResponseStatusException(HttpStatus.resolve(e.status()), "Houve um problema ao criar a carteira, tente novamente");
        }
    }



}
