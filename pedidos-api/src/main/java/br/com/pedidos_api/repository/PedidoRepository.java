package br.com.pedidos_api.repository;

import br.com.pedidos_api.domain.entity.Pedido;
import br.com.pedidos_api.domain.enums.SituacaoPedidoEnum;
import br.com.pedidos_api.repository.custom.PedidoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoCustomRepository {

    @Modifying
    @Query("UPDATE Pedido p SET p.situacao = :situacao, p.dataAtualizacao = CURRENT_TIMESTAMP WHERE p.idPedido = :idPedido")
    void atualizarSituacaoPedido(Long idPedido, SituacaoPedidoEnum situacao);

}