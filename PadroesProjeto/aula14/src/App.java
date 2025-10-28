import classes.VendingMachine;

public class App {
    public static void main(String[] args) throws Exception {
        VendingMachine machine = new VendingMachine();

        machine.insertCoin(); // HasCoin
        machine.insertCoin(); // HasCoin
        machine.ejectCoin(); // NoCoin
        machine.selectProduct(); // NoCoin
        machine.insertCoin(); // HasCoin
        machine.selectProduct(); // NoCoin
        machine.ejectCoin(); // NoCoin
    }
}
