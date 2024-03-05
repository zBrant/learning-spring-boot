package io.github.zbrant.service.impl;

import io.github.zbrant.domain.entity.Cliente;
import io.github.zbrant.domain.entity.ItemPedido;
import io.github.zbrant.domain.entity.Pedido;
import io.github.zbrant.domain.entity.Produto;
import io.github.zbrant.domain.enums.StatusPedido;
import io.github.zbrant.domain.repository.Clientes;
import io.github.zbrant.domain.repository.ItensPedido;
import io.github.zbrant.domain.repository.Pedidos;
import io.github.zbrant.domain.repository.Produtos;
import io.github.zbrant.exception.PedidoNaoEncontradoException;
import io.github.zbrant.exception.RegraDeNegocioException;
import io.github.zbrant.rest.dto.ItemPedidoDTO;
import io.github.zbrant.service.PedidoService;
import io.github.zbrant.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

  private final Pedidos repository;
  private final Clientes clientesRepository;
  private final Produtos produtosRepository;
  private final ItensPedido itemsPedidoRepository;

  @Override
  @Transactional
  public Pedido salvar(PedidoDTO dto) {
    Integer idCliente = dto.getCliente();
    Cliente cliente = clientesRepository.findById(idCliente)
        .orElseThrow(() -> new RegraDeNegocioException("Código de cliente invalido"));

    Pedido pedido = new Pedido();
    pedido.setTotal(dto.getTotal());
    pedido.setDataPedido(LocalDate.now());
    pedido.setCliente(cliente);
    pedido.setStatus(StatusPedido.REALIZADO);

    List<ItemPedido> itemsPedidos = converterItems(pedido, dto.getItems());
    repository.save(pedido);
    itemsPedidoRepository.saveAll(itemsPedidos);
    pedido.setItens(itemsPedidos);
    return pedido;
  }

  @Override
  public Optional<Pedido> obterPedidoCompleto(Integer id) {
    return repository.findByIdFetchItens(id);
  }

  @Override
  @Transactional
  public void atualizaStatus(Integer id, StatusPedido statusPedido) {
    repository
        .findById(id)
        .map(pedido ->  {
          pedido.setStatus(statusPedido);
          return repository.save(pedido);
        }).orElseThrow(() -> new PedidoNaoEncontradoException());
  }

  private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
    if (items.isEmpty()){
      throw new RegraDeNegocioException("Não é possivel realizar um pedido sem itens");
    }

    return items
        .stream()
        .map(dto -> {
          Integer idProduto = dto.getProduto();
          Produto produto = produtosRepository
              .findById(idProduto)
              .orElseThrow(
                  () -> new RegraDeNegocioException(
                      "Código do produto invalido : " + idProduto
                  ));

          ItemPedido itemPedido = new ItemPedido();
          itemPedido.setQuantidade(dto.getQuantidade());
          itemPedido.setPedido(pedido);
          itemPedido.setProduto(produto);
          return itemPedido;
        }).collect(Collectors.toList());
  }
}
