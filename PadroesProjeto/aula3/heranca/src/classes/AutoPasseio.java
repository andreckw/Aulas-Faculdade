package classes;

public class AutoPasseio extends Veiculo {
    private Integer portas = 2;

    public AutoPasseio(String modelo, String cor, boolean situacao, Integer portas) {
        super(modelo, cor, situacao);
        this.portas = portas;
    }

    @Override
    public Integer getInfo() {
        return portas;
    }
}
