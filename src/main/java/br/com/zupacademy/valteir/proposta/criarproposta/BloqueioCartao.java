package br.com.zupacademy.valteir.proposta.criarproposta;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime instanteBloqueio = LocalDateTime.now();
    private String userAgent;
    private String remoteAddr;
    @OneToOne
    @JoinColumn(unique = true)
    private Cartao cartao;

    @Deprecated
    private BloqueioCartao() {}

    public BloqueioCartao(String userAgent, String remoteAddr, Cartao cartao) {
        this.userAgent = userAgent;
        this.remoteAddr = remoteAddr;
        this.cartao = cartao;
    }
}
