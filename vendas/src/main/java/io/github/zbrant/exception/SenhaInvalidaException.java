package io.github.zbrant.exception;

public class SenhaInvalidaException extends RuntimeException {

  public SenhaInvalidaException() {
    super("Senha inválida");
  }
}
