package br.com.zupacademy.valteir.proposta.criarproposta;

import br.com.zupacademy.valteir.proposta.config.validators.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {
    @NotBlank
    @Document
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(EntityManager manager) {
        if(jaExiste(manager))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta cadastrada para o solicitante");

        return new Proposta(documento, email, nome, endereco, salario);
    }

    private boolean jaExiste(EntityManager manager) {
        Query query = manager.createQuery("select 1 from Proposta p where p.documento = :pDocumento");
        query.setParameter("pDocumento", documento);
        return query.getResultList().iterator().hasNext();
    }


    public String getEmail() {
        return email;
    }
}
