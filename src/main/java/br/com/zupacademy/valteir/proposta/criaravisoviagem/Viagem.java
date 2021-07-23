package br.com.zupacademy.valteir.proposta.criaravisoviagem;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destino;
    private String remoteAddr;
    private String userAgent;
    private LocalDateTime instanteCriacao = LocalDateTime.now();
    private LocalDate dataTermino;
    @ManyToOne
    private Cartao cartao;

    public Viagem(@NotNull Cartao cartao, String destino, LocalDate dataTermino, String userAgent, String remoteAddr) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.remoteAddr = remoteAddr;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }
}
