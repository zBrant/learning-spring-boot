package io.github.zbrant.testesunitarios.servicos;

import org.junit.Test;
import org.mockito.Mockito;

public class CalculadoraMockTest {

    @Test
    public void teste(){
        Calculadora calc = Mockito.mock(Calculadora.class);
        Mockito.when(calc.somar(Mockito.eq(1), Mockito.anyInt())).thenReturn(5);

        System.out.println(calc.somar(1,98758));
    }
}
