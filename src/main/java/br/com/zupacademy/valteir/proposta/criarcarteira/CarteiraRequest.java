package br.com.zupacademy.valteir.proposta.criarcarteira;

import br.com.zupacademy.valteir.proposta.criarproposta.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "PAYPAL")
    private String carteira;

    public CarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(email, carteira, cartao);
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
