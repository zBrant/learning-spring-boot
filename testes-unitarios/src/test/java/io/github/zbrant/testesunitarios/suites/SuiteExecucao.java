package io.github.zbrant.testesunitarios.suites;

import io.github.zbrant.testesunitarios.servicos.CalculadoraTest;
import io.github.zbrant.testesunitarios.servicos.CalculoValorLocacaoTest;
import io.github.zbrant.testesunitarios.servicos.LocacaoServiceTeste;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculadoraTest.class,
        CalculoValorLocacaoTest.class,
        LocacaoServiceTeste.class
})
public class SuiteExecucao {

}
