package io.github.zbrant.testesunitarios.servicos;

import io.github.zbrant.testesunitarios.exceptions.NaoPodeDividirPorZeroException;
import io.github.zbrant.testesunitarios.runners.ParallelRunner;
import org.junit.*;
import org.junit.runner.RunWith;

public class CalculadoraTest {

    public static StringBuffer ordem = new StringBuffer();
    private Calculadora calc;

    @Before
    public void setup(){
        calc = new Calculadora();
        System.out.println("Iniciando...");
        ordem.append("1");
    }

    @After
    public void tearDown(){
        System.out.println("Finalizando...");
    }

    @AfterClass
    public static void tearDownClass(){
        System.out.println(ordem.toString());
    }

    @Test
    public void deveSomarDoisValores(){
        // cenario
        int a = 5;
        int b = 3;

        // acao
        int resultado = calc.somar(a, b);

        //verificacao
        Assert.assertEquals(8, resultado);
    }

    @Test
    public void deveSubtrairDoisValores(){
        // cenario
        int a = 8;
        int b = 5;

        //acao
        int resultado = calc.subrair(a, b);

        //verificacao
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException{
        // cenario
        int a = 6;
        int b = 3;

        //acao
        int resultado = calc.dividir(a, b);

        //verificacao
        Assert.assertEquals(2, resultado);
    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException{
        int a = 10;
        int b = 0;

        //acao
        calc.dividir(a, b);
    }

    @Test
    public void deveDividir(){
        String a = "6";
        String b = "3";

        int resultado = calc.dividir(a, b);

        Assert.assertEquals(2, resultado);
    }
}
