package classes;

import interfaces.State;

public class VendingMachine {
    
    public State estado;

    public VendingMachine() {
        this.estado = new NoCoinState(this);
    }

    public void insertCoin() {
        estado.insertCoin();
    }

    public void ejectCoin() {
        estado.ejectCoin();
    }

    public void selectProduct() {
        estado.selectProduct();
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

}
