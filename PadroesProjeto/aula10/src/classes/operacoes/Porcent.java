package classes.operacoes;

import interfaces.IStragtegy;

public class Porcent implements IStragtegy {

    @Override
    public double executar(double a, double b) {
        return a / b * 100;
    }
    
}
