package io.github.zbrant.sbootexpsecurity.api.dto;

import io.github.zbrant.sbootexpsecurity.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
  private Usuario usuario;
  private List<String> permissoes;
}
