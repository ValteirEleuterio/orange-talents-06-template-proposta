package br.com.zupacademy.valteir.proposta.criaravisoviagem;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class CriarAvisoViagemController {

    private EntityManager manager;

    public CriarAvisoViagemController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping("/cartoes/{id}/viagens")
    @Transactional
    public ResponseEntity<?> criar(@Valid @RequestBody ViagemRequest viagemRequest,
                                   @PathVariable("id") Long idCartao,
                                   @RequestHeader(value = "User-Agent") String userAgent,
                                   HttpServletRequest request) {

        Cartao cartao = manager.find(Cartao.class, idCartao);

        if(Objects.isNull(cartao))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum cartão com o id: "+idCartao);

        Viagem viagem = viagemRequest.toModel(cartao, userAgent, request.getRemoteAddr());

        manager.persist(viagem);

        return ResponseEntity.ok().build();
    }
}
