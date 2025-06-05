package br.com.estoque_api.repositories;

import br.com.estoque_api.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    @Modifying
    @Query("UPDATE Produto p SET p.quantidadeDisponivel = :quantidadeDisponivel WHERE p.idProduto = :idProduto")
    void atualizarQuantidadeDisponivel(Long idProduto, BigDecimal quantidadeDisponivel);

}