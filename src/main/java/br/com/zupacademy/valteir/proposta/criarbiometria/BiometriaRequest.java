package br.com.zupacademy.valteir.proposta.criarbiometria;

import br.com.zupacademy.valteir.proposta.config.validators.IsBase64;
import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @IsBase64
    @NotBlank
    private String fingerprint;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(cartao, fingerprint);
    }
}
