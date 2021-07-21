package br.com.zupacademy.valteir.proposta.criarbiometria;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fingerprint;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @ManyToOne
    private Cartao cartao;

    public Biometria(@NotNull Cartao cartao, @NotBlank String fingerprint) {
        this.cartao = cartao;
        this.fingerprint = fingerprint;
    }

    public Long getId() {
        return id;
    }
}
