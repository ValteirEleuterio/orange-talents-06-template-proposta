package br.com.zupacademy.valteir.proposta.criarbiometria;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class CriarBiometriaController {

    private EntityManager manager;

    public CriarBiometriaController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping("/biometrias/{idCartao}")
    @Transactional
    public ResponseEntity<?> criar(@PathVariable Long idCartao,
                                   @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder) {
        Cartao cartao = manager.find(Cartao.class, idCartao);

        if(Objects.isNull(cartao))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum cartão com id: "+idCartao);

        Biometria biometria = request.toModel(cartao);

        manager.persist(biometria);

        return ResponseEntity.created(
                uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri()
        ).build();
    }
}
