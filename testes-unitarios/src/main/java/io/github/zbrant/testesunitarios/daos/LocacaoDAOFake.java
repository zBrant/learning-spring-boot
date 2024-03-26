package io.github.zbrant.testesunitarios.daos;

import io.github.zbrant.testesunitarios.entidades.Locacao;

import java.util.List;

public class LocacaoDAOFake implements LocacaoDAO{

    @Override
    public void salvar(Locacao locacao) {

    }

    @Override
    public List<Locacao> obterLocacoesPendentes() {
        return null;
    }
}
