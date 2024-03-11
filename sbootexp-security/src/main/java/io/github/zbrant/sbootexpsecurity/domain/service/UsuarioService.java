package io.github.zbrant.sbootexpsecurity.domain.service;

import io.github.zbrant.sbootexpsecurity.domain.entity.Grupo;
import io.github.zbrant.sbootexpsecurity.domain.entity.Usuario;
import io.github.zbrant.sbootexpsecurity.domain.entity.UsuarioGrupo;
import io.github.zbrant.sbootexpsecurity.domain.repository.GrupoRepository;
import io.github.zbrant.sbootexpsecurity.domain.repository.UsuarioGrupoRepository;
import io.github.zbrant.sbootexpsecurity.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
  private final UsuarioRepository repository;
  private final GrupoRepository grupoRepository;
  private final UsuarioGrupoRepository usuarioGrupoRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public Usuario salvar(Usuario usuario, List<String> grupos){
    String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
    usuario.setSenha(senhaCriptografada);
    repository.save(usuario);

    List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
      Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
      if (possivelGrupo.isPresent()) {
        Grupo grupo = possivelGrupo.get();
        return new UsuarioGrupo(usuario, grupo);
      }
      return null;
    }).filter(Objects::nonNull).toList();
    usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

    return usuario;
  }

  public Usuario obterUsuarioComPermissoes(String login){
    Optional<Usuario> usuarioOptional = repository.findByLogin(login);
    if(usuarioOptional.isEmpty()){
      return null;
    }

    Usuario usuario = usuarioOptional.get();
    List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
    usuario.setPermissoes(permissoes);

    return usuario;
  }
}
