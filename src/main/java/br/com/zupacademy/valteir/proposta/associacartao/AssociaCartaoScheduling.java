package br.com.zupacademy.valteir.proposta.associacartao;

import br.com.zupacademy.valteir.proposta.outrossistemas.cartoesapi.CartaoResponse;
import br.com.zupacademy.valteir.proposta.outrossistemas.cartoesapi.CartoesApi;
import br.com.zupacademy.valteir.proposta.criarproposta.Proposta;
import br.com.zupacademy.valteir.proposta.utils.ExecutorTransacao;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AssociaCartaoScheduling {

    private EntityManager manager;
    private CartoesApi cartoesApi;
    private ExecutorTransacao executorTransacao;

    public AssociaCartaoScheduling(EntityManager manager, CartoesApi cartoesApi, ExecutorTransacao executorTransacao) {
        this.manager = manager;
        this.cartoesApi = cartoesApi;
        this.executorTransacao = executorTransacao;
    }

    @Scheduled(fixedDelayString = "${cartoes.periodicidade}")
    public void processa() {
        String sql = "select p from Proposta p where p.estado = 'ELEGIVEL' and p.cartao is null";
        TypedQuery<Proposta> query = manager.createQuery(sql, Proposta.class);
        List<Proposta> propostasSemCartao = query.getResultList();

        propostasSemCartao.forEach(proposta -> {
            try {
                CartaoResponse cartao = cartoesApi.cartoes(String.valueOf(proposta.getId()));
                proposta.setCartao(cartao.toModel());
                executorTransacao.atualizaEComita(proposta);
            } catch (FeignException e) { }
        });
    }

}
