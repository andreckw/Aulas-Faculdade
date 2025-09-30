package classes;

public class AcucarDecorator extends Decorator {

    public AcucarDecorator(IBebidas bebidas) {
        super(bebidas);
    }

    @Override
    public String Descricao() {
        return bebidas.Descricao() + " + acucar";
    }

    @Override
    public double Custo() {
        return bebidas.Custo() + 0.1;
    }
    
}
