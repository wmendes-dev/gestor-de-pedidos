package br.com.pagamentos_api.schedule;

import br.com.pagamentos_api.domain.entity.EventoOutbox;
import br.com.pagamentos_api.domain.enums.SituacaoEventoOutboxEnum;
import br.com.pagamentos_api.kafka.producer.EventoProducerFactory;
import br.com.pagamentos_api.kafka.producer.IEventoProducer;
import br.com.pagamentos_api.service.EventoOutboxService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoOutboxSchedule {

    private final EventoOutboxService eventoOutboxService;

    private final EventoProducerFactory eventoProducerFactory;

    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    public void processarEventosPendentes() {
        List<EventoOutbox> eventoOutboxList = this.eventoOutboxService.listarEventosPendentes();

        eventoOutboxList.forEach(eventoOutbox -> {
            IEventoProducer<Object> producer = (IEventoProducer<Object>) this.eventoProducerFactory.getProducer(eventoOutbox.getTipoEvento());

            if (producer == null) return;

            try {
                Object evento = desserializar(eventoOutbox.getPayload(), producer);
                producer.publicarEvento(evento)
                        .thenAccept(sucesso -> {
                            this.eventoOutboxService.atualizarSituacaoEvento(
                                    eventoOutbox,
                                    SituacaoEventoOutboxEnum.PROCESSADO
                            );
                        }).exceptionally(erro -> {
                            // TODO -> implementar lógica de erro
                            System.out.println("-- ERRO: " + erro);
                            return null;
                        });
            } catch (Exception e) {
                System.out.println("-- ERRO: " + e);
            }
        });
    }

    private Object desserializar(String payload, IEventoProducer<?> producer) {
        Class<?> classePayload = producer.getTipoEvento().getClassePayload();

        if (classePayload.equals(String.class)) return payload;

        try {
            return this.objectMapper.readValue(payload, classePayload);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao desserializar payload para " + classePayload.getSimpleName(), e);
        }
    }

}