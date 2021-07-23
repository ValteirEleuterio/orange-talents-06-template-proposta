package br.com.zupacademy.valteir.proposta.config.metricas;

import br.com.zupacademy.valteir.proposta.acompanharproposta.AcompanharPropostaController;
import br.com.zupacademy.valteir.proposta.criarproposta.CriarPropostaController;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MinhasMetricas {

    private final MeterRegistry meterRegistry;

    public MinhasMetricas(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void propostaCriada() {
        Collection<Tag> tags = new ArrayList<>();

        tags.add(Tag.of("emissora", "Mastercard"));
        tags.add(Tag.of("banco", "Itaú"));

        Counter contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);

        contadorDePropostasCriadas.increment();
    }
    

}
