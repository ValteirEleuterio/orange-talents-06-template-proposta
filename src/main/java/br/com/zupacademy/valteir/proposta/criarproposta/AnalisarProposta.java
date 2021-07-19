package br.com.zupacademy.valteir.proposta.criarproposta;

import br.com.zupacademy.valteir.proposta.analiseapi.AnaliseApi;
import br.com.zupacademy.valteir.proposta.analiseapi.AnaliseRequest;
import br.com.zupacademy.valteir.proposta.analiseapi.AnaliseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnalisarProposta {

    private AnaliseApi analiseApi;

    public AnalisarProposta(AnaliseApi analiseApi) {
        this.analiseApi = analiseApi;
    }

    public EstadoProposta processa(Proposta proposta) {
        try {
            AnaliseResponse analise  = analiseApi.analise(new AnaliseRequest(proposta));
            return analise.getResultadoSolicitacao().equals("SEM_RESTRICAO") ? EstadoProposta.ELEGIVEL : EstadoProposta.NAO_ELEGIVEL;
        } catch (FeignException.FeignClientException e) {
            try {
                AnaliseResponse analise = new ObjectMapper().readValue(e.contentUTF8(), AnaliseResponse.class);
                return analise.getResultadoSolicitacao().equals("SEM_RESTRICAO") ? EstadoProposta.ELEGIVEL : EstadoProposta.NAO_ELEGIVEL;
            } catch (JsonProcessingException jsonProcessingException) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro ao realizar analise da proposta de id " + proposta.getId());
            }
        }
    }
}
