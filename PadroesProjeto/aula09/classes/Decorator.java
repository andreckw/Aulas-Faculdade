package classes;

public abstract class Decorator implements IBebidas {
    
    protected IBebidas bebidas;

    public Decorator(IBebidas bebidas) {
        this.bebidas = bebidas;
    }
}
