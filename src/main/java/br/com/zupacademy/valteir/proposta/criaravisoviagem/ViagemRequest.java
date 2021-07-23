package br.com.zupacademy.valteir.proposta.criaravisoviagem;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViagemRequest {

    @NotBlank
    private String destino;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Future
    @NotNull
    private LocalDate dataTermino;

    public Viagem toModel(Cartao cartao, String userAgent, String remoteAddr) {
        return new Viagem(cartao, destino, dataTermino, userAgent, remoteAddr);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }
}
