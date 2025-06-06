package br.com.pedidos_api.repositories;

import br.com.pedidos_api.entities.Pedido;
import br.com.pedidos_api.enums.SituacaoEventoOutboxEnum;
import br.com.pedidos_api.enums.SituacaoPedidoEnum;
import br.com.pedidos_api.repositories.custom.PedidoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoCustomRepository {

    @Modifying
    @Query("UPDATE Pedido p SET p.situacao = :situacao WHERE p.idPedido = :idPedido")
    void atualizarSituacaoPedido(Long idPedido, SituacaoPedidoEnum situacao);

}