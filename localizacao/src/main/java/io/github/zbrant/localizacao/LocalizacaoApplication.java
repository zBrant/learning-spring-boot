package io.github.zbrant.localizacao;

import io.github.zbrant.localizacao.domain.entity.Cidade;
import io.github.zbrant.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

  @Autowired
  private CidadeRepository cidadeRepository;

  @Override
  public void run(String... args) throws Exception {
    listarCidaes();
  }

  @Transactional
  void salvarCidade(){
    var cidade = new Cidade(1L, "Sao paulo", 11396372L);
    cidadeRepository.save(cidade);
  }

  void listarCidaes(){
    cidadeRepository.findAll().forEach(System.out::println);
  }

  public static void main(String[] args) {
    SpringApplication.run(LocalizacaoApplication.class, args);
  }

}
