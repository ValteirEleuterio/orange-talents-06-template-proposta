package br.com.zupacademy.valteir.proposta.criarproposta;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;
    @OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private BloqueioCartao bloqueio;

    @Deprecated
    private Cartao() {}

    public Cartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void bloqueia(String userAgent, String remoteAddr) {
        if(bloqueio != null) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Não foi possível realizar o bloqueio pois o cartão já esta bloqueado"
            );
        }

        this.bloqueio = new BloqueioCartao(userAgent, remoteAddr, this);
    }

}
