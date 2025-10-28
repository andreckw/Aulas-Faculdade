package classes;

import interfaces.State;

public class HasCoinState implements State {

    private VendingMachine machine;

    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Ja tem um moeda, nao quero mais DINHEIRO");
    }

    @Override
    public void selectProduct() {
        System.out.println("Va embora porra");
        machine.setEstado(new NoCoinState(machine));
    }

    @Override
    public void ejectCoin() {
        System.out.println("NAO MINHA MOEDA NAO, NAO SOU NADA SEM ELA");
        machine.setEstado(new NoCoinState(machine));
    }
    
}
