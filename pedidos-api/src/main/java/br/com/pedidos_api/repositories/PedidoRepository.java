package br.com.pedidos_api.repositories;

import br.com.pedidos_api.entities.Pedido;
import br.com.pedidos_api.repositories.custom.PedidoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoCustomRepository {

}