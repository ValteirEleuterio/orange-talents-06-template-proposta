package br.com.zupacademy.valteir.proposta.config.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ErroResponse {

    private Collection<String> mensagens;

    public ErroResponse(@NotNull Collection<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }
}
