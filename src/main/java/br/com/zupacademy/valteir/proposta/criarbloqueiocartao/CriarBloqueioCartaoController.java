package br.com.zupacademy.valteir.proposta.criarbloqueiocartao;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;
import br.com.zupacademy.valteir.proposta.utils.ExecutorTransacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class CriarBloqueioCartaoController {

    private EntityManager manager;
    private ExecutorTransacao executorTransacao;
    private BloqueiaCartaoSistemaLegado bloqueiaCartaoSistemaLegado;

    public CriarBloqueioCartaoController(EntityManager manager, ExecutorTransacao executorTransacao, BloqueiaCartaoSistemaLegado bloqueiaCartaoSistemaLegado) {
        this.manager = manager;
        this.executorTransacao = executorTransacao;
        this.bloqueiaCartaoSistemaLegado = bloqueiaCartaoSistemaLegado;
    }

    @PostMapping("/cartoes/{id}/bloqueio")
    public ResponseEntity<?> bloquear(@PathVariable("id") Long idCartao,
                                      @RequestHeader(value = "User-Agent") String userAgent,
                                      HttpServletRequest request) {
        Cartao cartao = manager.find(Cartao.class, idCartao);

        if(Objects.isNull(cartao))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum cartão com o id: "+idCartao);

        cartao.bloqueia(userAgent, request.getRemoteAddr());
        executorTransacao.atualizaEComita(cartao);
        bloqueiaCartaoSistemaLegado.bloqueia(cartao);

        return ResponseEntity.ok().build();
    }
}
