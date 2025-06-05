package br.com.pedidos_api.repositories;

import br.com.pedidos_api.entities.EventoOutbox;
import br.com.pedidos_api.enums.SituacaoEventoOutboxEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoOutboxRepository extends JpaRepository<EventoOutbox, Long> {

    List<EventoOutbox> findBySituacao(SituacaoEventoOutboxEnum situacao);

    @Modifying
    @Query("UPDATE EventoOutbox e SET e.situacao = :situacao WHERE e.idEventoOutbox = :idEventoOutbox")
    void atualizarSituacaoEvento(Long idEventoOutbox, SituacaoEventoOutboxEnum situacao);

}