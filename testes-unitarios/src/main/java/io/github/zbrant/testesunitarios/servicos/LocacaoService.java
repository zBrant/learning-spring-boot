package io.github.zbrant.testesunitarios.servicos;

import io.github.zbrant.testesunitarios.daos.LocacaoDAO;
import io.github.zbrant.testesunitarios.entidades.Filme;
import io.github.zbrant.testesunitarios.entidades.Locacao;
import io.github.zbrant.testesunitarios.entidades.Usuario;
import io.github.zbrant.testesunitarios.exceptions.FilmeSemEstoqueException;
import io.github.zbrant.testesunitarios.exceptions.LocadoraException;
import io.github.zbrant.testesunitarios.utils.DataUtils;

import static io.github.zbrant.testesunitarios.utils.DataUtils.adicionarDias;

import java.util.*;

public class LocacaoService {

    private LocacaoDAO dao;
    private SPCService spcService;
    private EmailService emailService;

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {

        if(usuario == null) throw new LocadoraException("Usuario vazio");
        if(filmes == null || filmes.isEmpty()) throw new LocadoraException("Filme vazio");

        for (Filme filme: filmes) {
            if (filme.getEstoque() == 0) {
                throw new FilmeSemEstoqueException();
            }
        }

        boolean negativado;
        try {
             negativado = spcService.possuiNegativacao(usuario);
        } catch (Exception e) {
            throw new LocadoraException("Problemas com SPC, tente novamente");
        }

        if (negativado) throw new LocadoraException("Usuario Negativado");

        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(obterData());
        locacao.setValor(calcularValorLocacao(filmes));
        locacao.setFilmes(filmes);

        //Entrega no dia seguinte
        Date dataEntrega = obterData();
        dataEntrega = adicionarDias(dataEntrega, 1);
        if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
            dataEntrega = adicionarDias(dataEntrega, 1);
        }
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar
        dao.salvar(locacao);

        return locacao;
    }

    protected Date obterData() {
        return new Date();
    }

    private double calcularValorLocacao(List<Filme> filmes) {
        double valorTotal = 0;
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            double valorFilme = filme.getPrecoLocacao();

            switch(i){
                case 2: valorFilme *= 0.75; break;
                case 3: valorFilme *= 0.5; break;
                case 4: valorFilme *= 0.25; break;
                case 5: valorFilme *= 0d; break;
            }
            valorTotal += valorFilme;
        }
        return valorTotal;
    }

    public void notificarAtrasos(){
        List<Locacao> locacaoes = dao.obterLocacoesPendentes();
        for (Locacao locacao: locacaoes){
            if (locacao.getDataRetorno().before(new Date())) {
                emailService.notificarAtraso(locacao.getUsuario());
            }
        }
    }

    public void prorrogarLocacao(Locacao locacao, int dias){
        Locacao novaLocacao = new Locacao();
        novaLocacao.setUsuario(locacao.getUsuario());
        novaLocacao.setFilmes(locacao.getFilmes());
        novaLocacao.setDataLocacao(new Date());
        novaLocacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(dias));
        novaLocacao.setValor(locacao.getValor() * dias);
        dao.salvar(novaLocacao);
    }
}
