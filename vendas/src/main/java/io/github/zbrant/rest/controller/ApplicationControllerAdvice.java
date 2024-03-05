package io.github.zbrant.rest.controller;

import io.github.zbrant.exception.PedidoNaoEncontradoException;
import io.github.zbrant.exception.RegraDeNegocioException;
import io.github.zbrant.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(RegraDeNegocioException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleRegraNegocioException(RegraDeNegocioException ex){
    String mensagemErro = ex.getMessage();
    return new ApiErrors(mensagemErro);
  }

  @ExceptionHandler(PedidoNaoEncontradoException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex){
    return new ApiErrors(ex.getMessage());
  }
}
