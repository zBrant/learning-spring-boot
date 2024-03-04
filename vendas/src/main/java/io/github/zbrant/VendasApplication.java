package io.github.zbrant;

import io.github.zbrant.domain.entity.Cliente;
import io.github.zbrant.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

  @Bean
  public CommandLineRunner init(@Autowired Clientes clientes){
    return args -> {
      System.out.println("salvando clientes");
      clientes.save(new Cliente("Kratos"));
      clientes.save(new Cliente("Ronaldo"));

      List<Cliente> result = clientes.encontraPorNome("Kratos");
      result.forEach(System.out::println);

    };
  }

  public static void main(String[] args) {
    SpringApplication.run(VendasApplication.class, args);
  }
}
