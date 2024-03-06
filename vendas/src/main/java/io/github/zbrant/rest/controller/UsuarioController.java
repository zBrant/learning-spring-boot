package io.github.zbrant.rest.controller;


import io.github.zbrant.domain.entity.Usuario;
import io.github.zbrant.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final UsuarioServiceImpl usuarioService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario salvar(@RequestBody @Valid  Usuario usuario){
    String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
    usuario.setSenha(senhaCriptografada);
    return usuarioService.salvar(usuario);
  }
}
