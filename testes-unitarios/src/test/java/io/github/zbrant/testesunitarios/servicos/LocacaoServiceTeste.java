package io.github.zbrant.testesunitarios.servicos;

import io.github.zbrant.testesunitarios.entidades.Filme;
import io.github.zbrant.testesunitarios.entidades.Locacao;
import io.github.zbrant.testesunitarios.entidades.Usuario;
import io.github.zbrant.testesunitarios.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static io.github.zbrant.testesunitarios.utils.DataUtils.isMesmaData;

public class LocacaoServiceTeste {
    @Test
    public void teste() {
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}
