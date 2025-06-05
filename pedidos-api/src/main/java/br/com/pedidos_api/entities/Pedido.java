package br.com.pedidos_api.entities;

import br.com.pedidos_api.enums.SituacaoPedidoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Long idPedido;

    @Column(name = "VALOR_SUBTOTAL", nullable = false)
    private BigDecimal valorSubTotal;

    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;

    @Column(name = "VALOR_TOTAL", nullable = false)
    private BigDecimal valorTotal;

    @Embedded
    private ResumoCliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoPedido> produtosPedido = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoPedidoEnum situacao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    public Pedido() {
        this.situacao = SituacaoPedidoEnum.CRIADO;
        this.dataCriacao = LocalDateTime.now();
    }

}
