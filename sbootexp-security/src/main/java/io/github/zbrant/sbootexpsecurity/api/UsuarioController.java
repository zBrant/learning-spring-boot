package io.github.zbrant.sbootexpsecurity.api;

import io.github.zbrant.sbootexpsecurity.api.dto.CadastroUsuarioDTO;
import io.github.zbrant.sbootexpsecurity.domain.entity.Usuario;
import io.github.zbrant.sbootexpsecurity.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO body){
    Usuario usuarioSalvo = usuarioService.salvar(body.getUsuario(), body.getPermissoes());
    return ResponseEntity.ok(usuarioSalvo);
  }

}
