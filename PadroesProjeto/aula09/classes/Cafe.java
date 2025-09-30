package classes;

public class Cafe implements IBebidas {

    @Override
    public String Descricao() {
        return "Cafe";
    }

    @Override
    public double Custo() {
        return 3;
    }
    
}
