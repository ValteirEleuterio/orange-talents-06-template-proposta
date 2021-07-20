package br.com.zupacademy.valteir.proposta.acompanharproposta;

import br.com.zupacademy.valteir.proposta.criarproposta.Proposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class AcompanharPropostaController {

    private EntityManager manager;

    public AcompanharPropostaController(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> acompanhar(@PathVariable("id") Long idProposta) {
        Proposta proposta = manager.find(Proposta.class, idProposta);

        if(Objects.isNull(proposta))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada nenhuma proposta com o id: "+idProposta);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("estado", proposta.getEstado());

        return ResponseEntity.ok(resposta);
    }
}
