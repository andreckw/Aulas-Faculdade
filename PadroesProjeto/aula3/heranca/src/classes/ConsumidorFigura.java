package classes;

import interfaces.FiguraGeometrica;

public class ConsumidorFigura {
    public void desenhar(FiguraGeometrica f) {

        System.out.println(f.getNomeFigura());
        System.out.println(f.getArea());
        System.out.println(f.getPerimetro());

    }
}