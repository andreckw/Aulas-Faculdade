package FactoryMethod;

public class Desenhista {

    private iFabricaForma fabrica;

    public Desenhista(iFabricaForma fabrica) {
        this.fabrica = fabrica;
    }

    public void desenhar() {
        this.fabrica.criarForma().desenhar();
        // iForma forma = this.fabrica.criarForma();
        // forma.desenhar();
    }

}