package br.com.zupacademy.valteir.proposta.criarproposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;

    @Deprecated
    private Cartao() {}

    public Cartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
