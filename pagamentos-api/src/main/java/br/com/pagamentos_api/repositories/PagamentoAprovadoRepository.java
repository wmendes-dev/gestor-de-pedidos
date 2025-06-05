package br.com.pagamentos_api.repositories;

import br.com.pagamentos_api.entities.PagamentoAprovado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoAprovadoRepository extends JpaRepository<PagamentoAprovado, Long> {

}