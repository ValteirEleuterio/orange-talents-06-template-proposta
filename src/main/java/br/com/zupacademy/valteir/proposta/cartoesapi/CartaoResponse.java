package br.com.zupacademy.valteir.proposta.cartoesapi;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;

public class CartaoResponse {

    public String id;

    public Cartao toModel() {
        return new Cartao(id);
    }
}
