package br.com.zupacademy.valteir.proposta.criarbloqueiocartao;

import br.com.zupacademy.valteir.proposta.outrossistemas.cartoesapi.BloqueioRequest;
import br.com.zupacademy.valteir.proposta.outrossistemas.cartoesapi.CartoesApi;
import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;
import br.com.zupacademy.valteir.proposta.criarproposta.EstadoCartao;
import br.com.zupacademy.valteir.proposta.utils.ExecutorTransacao;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

@Service
public class BloqueiaCartaoSistemaLegado {

    private CartoesApi cartoesApi;
    private ExecutorTransacao executorTransacao;

    public BloqueiaCartaoSistemaLegado(CartoesApi cartoesApi, ExecutorTransacao executorTransacao) {
        this.cartoesApi = cartoesApi;
        this.executorTransacao = executorTransacao;
    }

    public void bloqueia(@NotNull Cartao cartao) {
        Assert.notNull(cartao, "cartão não pode ser nulo");

        try {
            EstadoCartao estadoCartao = cartoesApi.bloqueios(cartao.getNumeroCartao(),
                    new BloqueioRequest("proposta")).getResultado();
            cartao.setEstado(estadoCartao);

            executorTransacao.atualizaEComita(cartao);
        } catch (FeignException e) {}
    }
}
