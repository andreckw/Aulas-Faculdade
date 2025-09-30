package classes;

public class LeiteDecorator extends Decorator {

    public LeiteDecorator(IBebidas bebidas) {
            super(bebidas);
        }
    
        @Override
    public String Descricao() {
        return bebidas.Descricao() + " + leite";
    }

    @Override
    public double Custo() {
        return bebidas.Custo() + 0.5;
    }
    
}
