package br.com.estoque_api.service;

import br.com.estoque_api.domain.dto.request.ProdutoRequest;
import br.com.estoque_api.domain.dto.response.ProdutoResponse;
import br.com.estoque_api.domain.entity.Produto;
import br.com.estoque_api.exception.EntidadeNaoEncontradaException;
import br.com.estoque_api.mapper.ProdutoMapper;
import br.com.estoque_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ProdutoMapper produtoMapper;

    public ProdutoResponse obterProduto(Long idProduto) {
        Produto produto = obterProdutoPorId(idProduto);
        return this.produtoMapper.converterParaProdutoResponse(produto);
    }

    @Transactional
    public ProdutoResponse criarProduto(ProdutoRequest produtoRequest) {
        Produto produto = this.produtoMapper.converterParaProduto(produtoRequest);
        produto = this.produtoRepository.save(produto);
        return this.produtoMapper.converterParaProdutoResponse(produto);
    }

    @Transactional
    public ProdutoResponse atualizarProduto(Long idProduto, ProdutoRequest produtoRequest) {
        Produto produto = obterProdutoPorId(idProduto);
        this.produtoMapper.converterParaProduto(produto, produtoRequest);
        produto = this.produtoRepository.save(produto);
        return this.produtoMapper.converterParaProdutoResponse(produto);
    }

    public Produto obterProdutoPorId(Long idProduto) {
        return this.produtoRepository.findById(idProduto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado", idProduto));
    }

    @Transactional
    public void atualizarQuantidadeDisponivel(Produto produto, BigDecimal quantidadeDisponivel) {
        this.produtoRepository.atualizarQuantidadeDisponivel(produto.getIdProduto(), quantidadeDisponivel);
    }

}
