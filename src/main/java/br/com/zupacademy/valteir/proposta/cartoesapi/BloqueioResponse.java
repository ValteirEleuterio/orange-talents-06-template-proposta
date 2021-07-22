package br.com.zupacademy.valteir.proposta.cartoesapi;

import br.com.zupacademy.valteir.proposta.criarproposta.EstadoCartao;

public class BloqueioResponse {

    private String resultado;

    public EstadoCartao getResultado() {
        return "BLOQUEADO".equals(resultado) ? EstadoCartao.BLOQUEADO : EstadoCartao.DESBLOQUEADO;
    }
}
