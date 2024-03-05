package io.github.zbrant.rest.controller;

import io.github.zbrant.exception.PedidoNaoEncontradoException;
import io.github.zbrant.exception.RegraDeNegocioException;
import io.github.zbrant.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex){
    List<String> erros = ex.getBindingResult().getAllErrors()
        .stream()
        .map(erro -> erro.getDefaultMessage())
        .collect(Collectors.toList());
    return new ApiErrors(erros);
  }
}
