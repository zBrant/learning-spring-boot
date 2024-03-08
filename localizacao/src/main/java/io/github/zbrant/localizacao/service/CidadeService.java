package io.github.zbrant.localizacao.service;

import io.github.zbrant.localizacao.domain.entity.Cidade;
import io.github.zbrant.localizacao.domain.repository.CidadeRepository;
import static io.github.zbrant.localizacao.domain.repository.specs.CidadeSpecs.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CidadeService {

  private final CidadeRepository repository;

  public CidadeService(CidadeRepository repository){
    this.repository = repository;
  }

  @Transactional
  void salvarCidade(){
    var cidade = new Cidade(1L, "Sao paulo", 11396372L);
    repository.save(cidade);
  }

  void listarCidades(){
    repository.findAll().forEach(System.out::println);
  }

  public void listarCidadesPorNome(){
    Pageable pageable = PageRequest.of(1, 2);
    repository.findByNomeLike("%%%%", pageable).forEach(System.out::println);
  }

  public void listarCidadesPorNomeSQL(){
    repository.findByNomeSqlNativo("Sao Paulo")
        .stream().map(cidade -> new Cidade(cidade.getId(), cidade.getNome(), null))
        .forEach(System.out::println);
  }

  public void listarCidadesPorHabitantes(){
    repository.findByHabitantes(12396372L).forEach(System.out::println);
  }

  public void listarCidadesPorQuantidadeHabitantes(){
    repository.findByHabitantesLessThanAndNomeLike(1000001L, "Br%").forEach(System.out::println);
  }

  public List<Cidade> filtroDinamico(Cidade cidade){
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
    Example<Cidade> example = Example.of(cidade, matcher);
    return repository.findAll(example);
  }

  public void listarCidadeSpecsByNomeSpec(){
    repository
        .findAll(nomeEqual("Sao Paulo").and(idEqual(1L)))
        .forEach(System.out::println);
  }

  public void listarCidadeSpecsFiltroDinamico(Cidade filtro){
    // select * from cidade where 1=1
    Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

    if (filtro.getId() != null){
      specs = specs.and(idEqual(filtro.getId()));
    }

    if (StringUtils.hasText(filtro.getNome())){
      specs = specs.and(nomeLike(filtro.getNome()));
    }

    if(filtro.getHabitantes() != null){
      specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
    }

    repository.findAll(specs).forEach(System.out::println);

  }
}
