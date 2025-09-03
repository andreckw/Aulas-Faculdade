package classes;

public class Cliente {
    private Integer contaId;
    private double saldo;

    Cliente(Integer contaId) {
        this.contaId = contaId;
        this.saldo = 0;
    }

    public Integer getContaId() {
        return contaId;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }
}
