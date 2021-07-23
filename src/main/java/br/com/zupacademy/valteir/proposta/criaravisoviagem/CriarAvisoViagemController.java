package br.com.zupacademy.valteir.proposta.criaravisoviagem;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;
import br.com.zupacademy.valteir.proposta.utils.ExecutorTransacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class CriarAvisoViagemController {

    private EntityManager manager;
    private ExecutorTransacao executorTransacao;
    private CriaAvisoViagemSistemaLegado criaAvisoViagemSistemaLegado;

    public CriarAvisoViagemController(EntityManager manager, ExecutorTransacao executorTransacao, CriaAvisoViagemSistemaLegado criaAvisoViagemSistemaLegado) {
        this.manager = manager;
        this.executorTransacao = executorTransacao;
        this.criaAvisoViagemSistemaLegado = criaAvisoViagemSistemaLegado;
    }

    @PostMapping("/cartoes/{id}/viagens")
    public ResponseEntity<?> criar(@Valid @RequestBody ViagemRequest viagemRequest,
                                   @PathVariable("id") Long idCartao,
                                   @RequestHeader(value = "User-Agent") String userAgent,
                                   HttpServletRequest request) {

        Cartao cartao = manager.find(Cartao.class, idCartao);

        if(Objects.isNull(cartao))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum cartão com o id: "+idCartao);

        criaAvisoViagemSistemaLegado.notifica(cartao.getNumeroCartao(), viagemRequest);

        Viagem viagem = viagemRequest.toModel(cartao, userAgent, request.getRemoteAddr());
        executorTransacao.salvaEComita(viagem);

        return ResponseEntity.ok().build();
    }
}
