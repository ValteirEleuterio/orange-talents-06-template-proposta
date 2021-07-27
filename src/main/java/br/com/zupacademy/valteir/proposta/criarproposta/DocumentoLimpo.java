package br.com.zupacademy.valteir.proposta.criarproposta;

import org.springframework.security.crypto.encrypt.Encryptors;

public class DocumentoLimpo {

    private static final String salt = "a091f534e9dd9c8d";
    private String documento;

    public DocumentoLimpo(String documento) {
        this.documento = documento;
    }

    public String encrypt() {
        return Encryptors.text("documento", salt).encrypt(documento);
    }

    public static String decrypt(String documento) {
        return Encryptors.text("documento", salt).decrypt(documento);
    }

}
