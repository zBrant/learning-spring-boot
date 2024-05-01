package io.github.zbrant.testesunitarios.servicos;

import io.github.zbrant.testesunitarios.entidades.Usuario;

public interface EmailService {
    public void notificarAtraso(Usuario usuario);
}
