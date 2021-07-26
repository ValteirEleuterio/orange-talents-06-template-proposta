package br.com.zupacademy.valteir.proposta.criarcarteira;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;
import br.com.zupacademy.valteir.proposta.utils.ExecutorTransacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class CriarCarteiraController {

    private final EntityManager manager;
    private final CriarCarteiraSistemaLegado criarCarteiraSistemaLegado;
    private final ExecutorTransacao executorTransacao;

    public CriarCarteiraController(EntityManager manager, CriarCarteiraSistemaLegado criarCarteiraSistemaLegado, ExecutorTransacao executorTransacao) {
        this.manager = manager;
        this.criarCarteiraSistemaLegado = criarCarteiraSistemaLegado;
        this.executorTransacao = executorTransacao;
    }

    @PostMapping("/cartoes/{id}/carteiras")
    public ResponseEntity<?> criar(@PathVariable("id") Long idCartao,
                                   @Valid @RequestBody CarteiraRequest request,
                                   UriComponentsBuilder uriBuilder) {
        Cartao cartao = manager.find(Cartao.class, idCartao);

        if(Objects.isNull(cartao))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum cartão com o id: "+idCartao);

        criarCarteiraSistemaLegado.criar(cartao.getNumeroCartao(), request);

        Carteira carteira = request.toModel(cartao);
        executorTransacao.salvaEComita(carteira);

        return ResponseEntity.created(uriBuilder.path("/cartoes/{id}/carteiras/{id}")
                .buildAndExpand(cartao.getId(), carteira.getId()).toUri()).build();

    }

}
