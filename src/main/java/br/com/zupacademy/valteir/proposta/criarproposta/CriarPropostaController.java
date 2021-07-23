package br.com.zupacademy.valteir.proposta.criarproposta;

import br.com.zupacademy.valteir.proposta.config.metricas.MinhasMetricas;
import br.com.zupacademy.valteir.proposta.utils.ExecutorTransacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
public class CriarPropostaController {

    private EntityManager manager;
    private AnalisarProposta analisarProposta;
    private ExecutorTransacao executorTransacao;
    private MinhasMetricas metricas;

    public CriarPropostaController(EntityManager manager, AnalisarProposta analisarProposta, ExecutorTransacao executorTransacao, MinhasMetricas metricas) {
        this.manager = manager;
        this.analisarProposta = analisarProposta;
        this.executorTransacao = executorTransacao;
        this.metricas = metricas;
    }

    @PostMapping("/propostas")
    public ResponseEntity<?> criar(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder uriBuilder) {
        Proposta proposta = request.toModel(manager);
        executorTransacao.salvaEComita(proposta);

        EstadoProposta estado = analisarProposta.processa(proposta);

        proposta.setEstado(estado);
        executorTransacao.atualizaEComita(proposta);

        metricas.propostaCriada();
        return ResponseEntity.created(uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }
}
