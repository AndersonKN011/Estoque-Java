package com.estoque.controller;

import com.estoque.model.Produto;
import com.estoque.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PostMapping
    public Produto adicionar(@RequestBody Produto produtoRecebido ) {
        String erros = "";

        if (produtoRecebido.getNome() == null || produtoRecebido.getNome().isEmpty()){
            erros += "\nO nome não pode ser vazio!";
        }
        if (produtoRecebido.getPreco() < 0){
            erros += "\nO Valor não pode ser negativo!";
        }
        if (produtoRecebido.getQuantidade() < 1){
            erros += "\nA quantidade não pode ser menor que 1!";
        }

        if (!erros.isEmpty()){
            throw new RuntimeException(erros);
        }
        return repository.save(produtoRecebido);
    }

    @PutMapping("/{id}")
    public Produto editar (@PathVariable Long id, @RequestBody Produto dados){
        Produto produto = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());
        produto.setQuantidade(dados.getQuantidade());

        return repository.save(produto);
    }
}
