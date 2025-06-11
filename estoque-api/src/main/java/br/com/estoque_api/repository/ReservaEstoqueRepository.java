package br.com.estoque_api.repository;

import br.com.estoque_api.domain.entity.ReservaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservaEstoqueRepository extends JpaRepository<ReservaEstoque, Long> {

    Optional<ReservaEstoque> findByIdPedido(Long idPedido);

}