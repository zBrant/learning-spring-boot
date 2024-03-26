package io.github.zbrant.testesunitarios.servicos;

import io.github.zbrant.testesunitarios.builders.FilmeBuilder;
import io.github.zbrant.testesunitarios.builders.UsuarioBuilder;
import io.github.zbrant.testesunitarios.entidades.Filme;
import io.github.zbrant.testesunitarios.entidades.Locacao;
import io.github.zbrant.testesunitarios.entidades.Usuario;
import io.github.zbrant.testesunitarios.exceptions.FilmeSemEstoqueException;
import io.github.zbrant.testesunitarios.exceptions.LocadoraException;
import io.github.zbrant.testesunitarios.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.*;

import static io.github.zbrant.testesunitarios.builders.FilmeBuilder.*;
import static io.github.zbrant.testesunitarios.builders.UsuarioBuilder.*;
import static io.github.zbrant.testesunitarios.matchers.MatchersProprios.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LocacaoServiceTeste {

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup(){
        service = new LocacaoService();
    }

    @Test
    public void deveAlugarFilme() throws Exception {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(umFilme().comvalor(5.0).agora());

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        error.checkThat(locacao.getValor(), is(5.0));
        error.checkThat(locacao.getDataLocacao(), isHoje());
        error.checkThat(locacao.getDataRetorno(), isHojeComDiferencaDias(1));
    }

    @Test(expected= FilmeSemEstoqueException.class)
    public void naoDeveAlugarFilmeSemEstoque() throws Exception {
        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(umFilmeSemEstoque().agora());

        //acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
        //cenario
        List<Filme> filmes = Arrays.asList(umFilme().agora());

        //acao
        try {
            service.alugarFilme(null, filmes);
            fail();
        }  catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
    }

    @Test
    public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = umUsuario().agora();

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        //acao
        service.alugarFilme(usuario, null);
    }

    @Test
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = Arrays.asList(umFilme().agora());

        //acao
        Locacao retorno = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(retorno.getDataRetorno(), caiNumaSegunda());
    }

}
