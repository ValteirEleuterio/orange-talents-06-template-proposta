package br.com.zupacademy.valteir.proposta.criarproposta;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String documento;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private EstadoProposta estado;
    private String numeroCartao;

    @Deprecated
    private Proposta() {}

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void setEstado(EstadoProposta estado) {
        this.estado = estado;
    }

    public void setNumeroCartao(@NotNull String numeroCartao) {
        Assert.hasText(numeroCartao, "Houve um problema, o numero do cartão não pode ser nulo nem vazio");
        this.numeroCartao = numeroCartao;
    }
}
