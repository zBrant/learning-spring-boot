package io.github.zbrant;

import io.github.zbrant.domain.entity.Cliente;
import io.github.zbrant.domain.entity.Pedido;
import io.github.zbrant.domain.repository.Clientes;
import io.github.zbrant.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

  @Bean
  public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos){
    return args -> {
      System.out.println("salvando clientes");
      clientes.save(new Cliente("Kratos"));
      Cliente a = new Cliente("Ronaldo");
      clientes.save(a);

      Pedido p = new Pedido();
      p.setCliente(a);
      p.setDataPedido( LocalDate.now() );
      p.setTotal(BigDecimal.valueOf(100));

      pedidos.save(p);

//      Cliente cliente = clientes.findClienteFetchPedidos(a.getId());
//      System.out.println(cliente);
//      System.out.println(cliente.getPedidos());

      pedidos.findByCliente(a).forEach(System.out::println);
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(VendasApplication.class, args);
  }
}
