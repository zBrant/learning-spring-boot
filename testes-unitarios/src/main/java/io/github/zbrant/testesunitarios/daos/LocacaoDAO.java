package io.github.zbrant.testesunitarios.daos;

import io.github.zbrant.testesunitarios.entidades.Locacao;

import java.util.List;

public interface LocacaoDAO {

    public void salvar(Locacao locacao);

    List<Locacao> obterLocacoesPendentes();
}
