package br.com.pagamentos_api.repositories;

import br.com.pagamentos_api.entities.AnalisePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisePagamentoRepository extends JpaRepository<AnalisePagamento, Long> {

}