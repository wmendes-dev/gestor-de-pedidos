package br.com.estoque_api.repositories.specification;

import br.com.estoque_api.dtos.request.ProdutoRequestParams;
import br.com.estoque_api.entities.Produto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProdutoSpecification implements Specification<Produto> {

    private final ProdutoRequestParams produtoRequestParams;

    @Override
    public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(this.produtoRequestParams.nome())) {
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + this.produtoRequestParams.nome() + "%"));
        }

        Predicate[] predicateArray = new Predicate[predicates.size()];
        return criteriaBuilder.and(predicates.toArray(predicateArray));
    }

}