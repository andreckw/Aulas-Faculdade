package classes;

import interfaces.IStragtegy;

public class Calculadora {
    private IStragtegy stragtegy;

    public Calculadora(IStragtegy stragtegy) {
        setStragtegy(stragtegy);
    }

    public double calcular(double a, double b) {
        return this.stragtegy.executar(a, b);
    }

    public void setStragtegy(IStragtegy stragtegy) {
        this.stragtegy = stragtegy;
    }
}
