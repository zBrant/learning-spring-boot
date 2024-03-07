package io.github.zbrant.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "usuario")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  @NotEmpty(message = "{campo.login.obrigatorio}")
  private String login;
  @Column
  @NotEmpty(message = "{campo.senha.obrigatorio}")
  private String senha;
  @Column
  private boolean admin;
}
