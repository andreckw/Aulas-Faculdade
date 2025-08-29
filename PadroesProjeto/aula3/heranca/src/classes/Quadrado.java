package classes;

import interfaces.FiguraGeometrica;

public class Quadrado implements FiguraGeometrica {

    public String nome;
    public Integer lado;

    @Override
    public String getNomeFigura() {
        return this.nome;
    }

    @Override
    public Integer getArea() {
        return this.lado * this.lado;
    }

    @Override
    public Integer getPerimetro() {
        return this.lado*4;
    }
    
    
}
