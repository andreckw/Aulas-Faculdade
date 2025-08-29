package FactoryMethod;

public class FabricaCirculo implements iFabricaForma {

    @Override
    public iForma criarForma() {
        return new Circulo();
    }
}
