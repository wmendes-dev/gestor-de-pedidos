package br.com.pagamentos_api.repository;

import br.com.pagamentos_api.domain.entity.AnalisePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisePagamentoRepository extends JpaRepository<AnalisePagamento, Long> {

}