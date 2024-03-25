package io.github.zbrant.testesunitarios.servicos;

import io.github.zbrant.testesunitarios.entidades.Filme;
import io.github.zbrant.testesunitarios.entidades.Locacao;
import io.github.zbrant.testesunitarios.entidades.Usuario;
import static io.github.zbrant.testesunitarios.utils.DataUtils.adicionarDias;

import java.util.Date;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, Filme filme) {
        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }
}
