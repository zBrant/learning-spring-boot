package io.github.zbrant.localizacao;

import io.github.zbrant.localizacao.domain.entity.Cidade;
import io.github.zbrant.localizacao.domain.repository.CidadeRepository;
import io.github.zbrant.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

  @Autowired
  private CidadeService service;

  @Override
  public void run(String... args) throws Exception {
    service.listarCidadesPorNomeSQL();
  }

  public static void main(String[] args) {
    SpringApplication.run(LocalizacaoApplication.class, args);
  }

}
