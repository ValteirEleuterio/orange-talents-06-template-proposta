package br.com.zupacademy.valteir.proposta.criarproposta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CriarPropostaController {

    private EntityManager manager;

    public CriarPropostaController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> criar(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder uriBuilder) {
        Proposta proposta = request.toModel(manager);

        manager.persist(proposta);

        return ResponseEntity.created(uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
    }
}
