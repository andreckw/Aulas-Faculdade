package classes;

import interfaces.State;

public class NoCoinState implements State {

    private VendingMachine vendingMachine;

    public NoCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Foi adicionado uma moeda");
        vendingMachine.setEstado(new HasCoinState(vendingMachine));
    }

    @Override
    public void selectProduct() {
        System.out.println("Sem moedas seu estupido imundo, insira uma moeda");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Sem moedas seu estupido imundo");
    }
    
}
