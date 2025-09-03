package classes;

import java.util.List;

public class AccountManager {
    
    private static AccountManager instancia;
    private List<Cliente> clientes;


    private AccountManager() {
        this.clientes = new List<Cliente>() [
            new Cliente(1),
            new Cliente(2),
            new Cliente(3),
            new Cliente(3),
        ];
    }


    public static AccountManager getInstace(Integer contaId) {
        if (instancia == null) {
            instancia = new AccountManager();
        }

        return instancia;
    }

    public void depositar(Integer contaId, double valor) {
        for (Cliente c : clientes) {
            if (contaId == c.getContaId()) {
                c.setSaldo(valor + c.getSaldo());
                break;
            }
        }

    }

    public void sacar(Integer contaId, double valor) {
        for (Cliente c : clientes) {
            if (contaId == c.getContaId()) {
                c.setSaldo(valor - c.getSaldo());
                break;
            }
        }
    }

    public void verSaldo(Integer contaId) {
        for (Cliente c : clientes) {
            if (contaId == c.getContaId()) {
                System.out.println("Saldo atual: " + c.getSaldo());
                break;
            }
        }
    }

    public void listarContas() {
        for (Cliente c : this.clientes) {
            System.out.println("--------------------");
            System.err.println("Conta: "+c.getContaId());
            System.err.println("Saldo: "+c.getSaldo());
            System.out.println("--------------------");
        }
    }
}
