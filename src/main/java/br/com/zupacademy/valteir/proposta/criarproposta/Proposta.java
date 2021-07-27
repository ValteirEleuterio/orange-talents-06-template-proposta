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
    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    private Proposta() {}

    public Proposta(DocumentoLimpo documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento.encrypt();
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return DocumentoLimpo.decrypt(documento);
    }

    public String getNome() {
        return nome;
    }

    public void setEstado(EstadoProposta estado) {
        this.estado = estado;
    }

    public void setCartao(@NotNull Cartao cartao) {
        Assert.notNull(cartao, "Houve um problema, o cartão não pode ser nulo");
        this.cartao = cartao;
    }

    public EstadoProposta getEstado() {
        return estado;
    }
}
