package br.com.zupacademy.valteir.proposta.criarcarteira;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;

import javax.persistence.*;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String carteira;
    @ManyToOne
    private Cartao cartao;

    public Carteira(String email, String carteira, Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
