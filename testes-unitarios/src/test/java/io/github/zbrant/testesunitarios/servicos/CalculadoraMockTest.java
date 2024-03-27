package io.github.zbrant.testesunitarios.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class CalculadoraMockTest {

    @Mock
    private Calculadora calcMock;
    @Spy
    private Calculadora calcSpy;
    @Spy
    private EmailService emailSpy;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void devoMostrarDiferencaEntreMockSpy(){
//        Mockito.when(calcMock.somar(1,2)).thenCallRealMethod(8);
        Mockito.when(calcMock.somar(1,2)).thenReturn(8);
//        Mockito.when(calcSpy.somar(1,2)).thenReturn(8);
        Mockito.doReturn(5).when(calcSpy).somar(1,2);
        Mockito.doNothing().when(calcSpy).imprime();

        System.out.println("Mock: " + calcMock.somar(1,2));
        System.out.println("Spy: " + calcSpy.somar(1,2));

        System.out.println("Mock");
        calcMock.imprime();
        System.out.println("Spy");
        calcMock.imprime();
    }

    @Test
    public void teste(){
        Calculadora calc = Mockito.mock(Calculadora.class);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(calc.somar(argumentCaptor.capture(), argumentCaptor.capture())).thenReturn(5);

        Assert.assertEquals(5, calc.somar(345,98758));
        System.out.println(argumentCaptor.getAllValues());
    }
}
