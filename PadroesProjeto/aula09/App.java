import classes.AcucarDecorator;
import classes.Cafe;
import classes.IBebidas;
import classes.LeiteDecorator;

public class App {
    public static void main(String[] args) throws Exception {

        IBebidas bebida = new Cafe();

        System.out.println("Bebida: " + bebida.Descricao());
        System.out.println("-> Custo: " + bebida.Custo());

        bebida = new LeiteDecorator(bebida);
        System.out.println("Bebida: " + bebida.Descricao());
        System.out.println("-> Custo: " + bebida.Custo());

        bebida = new AcucarDecorator(bebida);
        System.out.println("Bebida: " + bebida.Descricao());
        System.out.println("-> Custo: " + bebida.Custo());
    }
}
