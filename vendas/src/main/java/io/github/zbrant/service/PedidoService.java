package io.github.zbrant.service;

import io.github.zbrant.domain.entity.Pedido;
import io.github.zbrant.domain.enums.StatusPedido;
import io.github.zbrant.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

  public Pedido salvar (PedidoDTO dto);
  Optional<Pedido> obterPedidoCompleto(Integer id);
  void atualizaStatus(Integer id, StatusPedido statusPedido);
}
