package br.com.estoque_api.entities;

import br.com.estoque_api.enums.SituacaoEventoOutboxEnum;
import br.com.estoque_api.enums.TipoEventoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_EVENTO_OUTBOX")
public class EventoOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO_OUTBOX")
    private Long idEventoOutbox;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_EVENTO", nullable = false)
    private TipoEventoEnum tipoEvento;

    @Column(name = "TIPO_AGREGADO", nullable = false)
    private String tipoAgregado;

    @Column(name = "ID_AGREGADO", nullable = false)
    private String idAgregado;

    @Lob
    @Column(name = "PAYLOAD", nullable = false)
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEventoOutboxEnum situacao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    public EventoOutbox() {
        this.situacao = SituacaoEventoOutboxEnum.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
    }

}
