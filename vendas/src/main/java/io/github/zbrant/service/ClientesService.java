package io.github.zbrant.service;

import ch.qos.logback.core.net.server.Client;
import io.github.zbrant.model.Cliente;
import io.github.zbrant.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

  private ClientesRepository repository;

  @Autowired
  public ClientesService(ClientesRepository repository){
    this.repository = repository;
  }

  public void salvarCliente(Cliente cliente){
    validarCliente(cliente);
    this.repository.persistir(cliente);
  }

  public void validarCliente(Cliente cliente){
    // aplica as validações
  }

}
