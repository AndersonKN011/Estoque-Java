package com.estoque.repository;

import com.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("select coalesce(sum(p.quantidade * p.preco), 0) from Produto p")
    Double calcularValorTotalEstoque();
}
