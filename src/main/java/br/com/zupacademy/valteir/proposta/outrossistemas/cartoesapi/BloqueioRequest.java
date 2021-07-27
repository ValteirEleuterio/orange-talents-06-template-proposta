package br.com.zupacademy.valteir.proposta.outrossistemas.cartoesapi;

public class BloqueioRequest {

    private String sistemaResponsavel;

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
