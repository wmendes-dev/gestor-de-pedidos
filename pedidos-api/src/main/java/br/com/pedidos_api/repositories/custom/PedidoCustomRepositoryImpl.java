package br.com.pedidos_api.repositories.custom;

import br.com.pedidos_api.dtos.request.PedidoRequestParams;
import br.com.pedidos_api.dtos.response.PedidoPesquisaResponse;
import br.com.pedidos_api.entities.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoCustomRepositoryImpl implements PedidoCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<PedidoPesquisaResponse> findAll(PedidoRequestParams pedidoRequestParams, Pageable pageable) {
        PedidoQuery pedidoQuery = new PedidoQuery();

        Long count = pedidoQuery.count(pedidoRequestParams);
        if (count.equals(0L)) return Page.empty(pageable);

        List<PedidoPesquisaResponse> list = pedidoQuery.list(pedidoRequestParams, pageable);

        return new PageImpl<>(list, pageable, count);
    }

    class PedidoQuery {

        private final CriteriaBuilder criteriaBuilder;
        private Root<Pedido> fromPedido;

        public PedidoQuery() {
            this.criteriaBuilder = entityManager.getCriteriaBuilder();
        }

        public Long count(PedidoRequestParams pedidoRequestParams) {
            CriteriaQuery<Long> queryCount = this.criteriaBuilder.createQuery(Long.class);

            prepararClausulas(queryCount);

            queryCount.select(this.criteriaBuilder.count(fromPedido.get("idPedido")));

            aplicarFiltros(queryCount, pedidoRequestParams);

            return entityManager.createQuery(queryCount).getSingleResult();
        }

        public List<PedidoPesquisaResponse> list(PedidoRequestParams pedidoRequestParams, Pageable pageable) {
            CriteriaQuery<PedidoPesquisaResponse> querySelect = this.criteriaBuilder.createQuery(PedidoPesquisaResponse.class);

            prepararClausulas(querySelect);

            querySelect.select(this.criteriaBuilder.construct(
                    PedidoPesquisaResponse.class,
                    this.fromPedido.get("idPedido").alias("idPedido"),
                    this.fromPedido.get("dataEmissao").alias("dataEmissao"),
                    this.fromPedido.get("valorTotal").alias("valorTotal"),
                    this.fromPedido.get("usuario").get("nomeCompleto").alias("usuario")
            ));

            aplicarFiltros(querySelect, pedidoRequestParams);

            TypedQuery<PedidoPesquisaResponse> query = entityManager.createQuery(querySelect);
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());

            return query.getResultList();
        }

        private void prepararClausulas(CriteriaQuery<?> criteriaQuery) {
            this.fromPedido = criteriaQuery.from(Pedido.class);
        }

        private void aplicarFiltros(CriteriaQuery<?> criteriaQuery, PedidoRequestParams pedidoRequestParams) {
            List<Predicate> predicates = new ArrayList<>();

            String usuario = pedidoRequestParams.usuario();
            if (!ObjectUtils.isEmpty(usuario)) {
                predicates.add(this.criteriaBuilder.like(this.fromPedido.get("usuario").get("nomeCompleto"), "%" + usuario + "%"));
            }

            LocalDate dataInicio = pedidoRequestParams.dataInicio();
            if (!ObjectUtils.isEmpty(dataInicio)) {
                predicates.add(this.criteriaBuilder.greaterThanOrEqualTo(this.fromPedido.get("dataEmissao"), dataInicio));
            }

            LocalDate dataFim = pedidoRequestParams.dataFim();
            if (!ObjectUtils.isEmpty(dataFim)) {
                predicates.add(this.criteriaBuilder.lessThanOrEqualTo(this.fromPedido.get("dataEmissao"), dataFim));
            }

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(predicates.toArray(predicateArray));
        }

    }

}