package br.com.pedidos_api.services;

import br.com.pedidos_api.entities.EventoOutbox;
import br.com.pedidos_api.enums.SituacaoEventoOutboxEnum;
import br.com.pedidos_api.enums.TipoEventoEnum;
import br.com.pedidos_api.repositories.EventoOutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoOutboxService {

    private final EventoOutboxRepository eventoOutboxRepository;

    private final ObjectMapper objectMapper;

    @Transactional
    public void criarEvento(Object payload, TipoEventoEnum tipoEventoEnum, String idAgregado) {
        try {
            String payloadStr = this.objectMapper.writeValueAsString(payload);

            EventoOutbox evento = new EventoOutbox();
            evento.setTipoEvento(tipoEventoEnum);
            evento.setTipoAgregado(tipoEventoEnum.getTipoAgregado());
            evento.setIdAgregado(idAgregado);
            evento.setPayload(payloadStr);

            this.eventoOutboxRepository.save(evento);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar payload do evento outbox", e);
        }
    }

    @Transactional(readOnly = true)
    public List<EventoOutbox> listarEventosPendentes() {
        return this.eventoOutboxRepository.findBySituacao(SituacaoEventoOutboxEnum.PENDENTE);
    }

    @Transactional
    public void atualizarSituacaoEvento(EventoOutbox eventoOutbox, SituacaoEventoOutboxEnum situacaoEventoOutboxEnum) {
        this.eventoOutboxRepository.atualizarSituacaoEvento(eventoOutbox.getIdEventoOutbox(), situacaoEventoOutboxEnum);
    }

}
