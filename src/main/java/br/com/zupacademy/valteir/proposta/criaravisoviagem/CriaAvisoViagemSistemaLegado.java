package br.com.zupacademy.valteir.proposta.criaravisoviagem;

import br.com.zupacademy.valteir.proposta.outrossistemas.cartoesapi.CartoesApi;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CriaAvisoViagemSistemaLegado {

    private CartoesApi cartoesApi;

    public CriaAvisoViagemSistemaLegado(CartoesApi cartoesApi) {
        this.cartoesApi = cartoesApi;
    }

    public void notifica(String numeroCartao, ViagemRequest request) {
        try {
            cartoesApi.avisos(numeroCartao, request);
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.resolve(e.status()), "Houve um problema ao criar aviso, tente novamente");
        }
    }
}
