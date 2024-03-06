package io.github.zbrant.rest.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
  private String login;
  private boolean admin;
}
