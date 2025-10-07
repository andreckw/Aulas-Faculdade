package classes.operacoes;

import interfaces.IStragtegy;

public class Somar implements IStragtegy {

    @Override
    public double executar(double a, double b) {
        return a + b;
    }
    
}
