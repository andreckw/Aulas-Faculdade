package classes;

import interfaces.FiguraGeometrica;

public class Triangulo  implements FiguraGeometrica {
    public String nome;
    public int ladoA;
    public int ladoB;
    public int ladoC;
    public int base;
    public int altura;

    @Override
    public Integer getArea() {
        return (this.altura+this.base)/2;
    }
    
    @Override
    public String getNomeFigura() {
        return this.nome;
    }
    @Override
    public Integer getPerimetro() {
        return (this.ladoA+this.ladoB+this.ladoC);
        
    }

    
}
