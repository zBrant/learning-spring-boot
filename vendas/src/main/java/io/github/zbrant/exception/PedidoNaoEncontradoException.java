package io.github.zbrant.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
  public PedidoNaoEncontradoException() {
    super("Pedido não encontrado");
  }
}
