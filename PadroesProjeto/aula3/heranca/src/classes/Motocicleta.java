package classes;

public class Motocicleta extends Veiculo {

    public Motocicleta(String modelo, String cor, boolean situacao) {
        super(modelo, cor, situacao);
    }

    @Override
    public Integer getInfo() {
        return 0;
    }

    
}
