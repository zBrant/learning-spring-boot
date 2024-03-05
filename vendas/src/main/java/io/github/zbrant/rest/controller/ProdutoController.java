package io.github.zbrant.rest.controller;

import io.github.zbrant.domain.entity.Produto;
import io.github.zbrant.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  private Produtos produtos;

  public ProdutoController(Produtos produtos) {
    this.produtos = produtos;
  }

  @RequestMapping("/{id}")
  public Produto getProdutoById(@PathVariable Integer id){
    return produtos.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto nao encontrado"));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Produto save(@RequestBody @Valid Produto produto){
    return produtos.save(produto);
  }

  @PostMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id){
    produtos.findById(id)
        .map(produto -> {
          produtos.delete(produto);
          return produto;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto nao encontrado"));
  }


  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto){
    produtos.findById(id)
        .map(produtoExistente ->{
          produto.setId(produtoExistente.getId());
          produtos.save(produto);
          return produtoExistente;
        })
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto nao encontrado"));
  }

  @GetMapping
  public List<Produto> find(Produto filtro){
    ExampleMatcher matcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example example = Example.of(filtro, matcher);
    return produtos.findAll(example);
  }
}
