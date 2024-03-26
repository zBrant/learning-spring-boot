package io.github.zbrant.testesunitarios.servicos;

import io.github.zbrant.testesunitarios.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {

    public int somar(int a, int b){
        return a + b;
    }

    public int subrair(int a, int b) {
        return a - b;
    }

    public int dividir(int a, int b) throws NaoPodeDividirPorZeroException{
        if (a == 0 || b == 0) throw new NaoPodeDividirPorZeroException();
        return a / b;
    }

    public int dividir(String a, String b){
        return Integer.valueOf(a) / Integer.valueOf(b);
    }
}
