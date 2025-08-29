import classes.AutoPasseio;
import classes.Cancela;
import classes.ConsumidorFigura;
import classes.Motocicleta;
import classes.Quadrado;
import classes.Triangulo;
import classes.Veiculo;

public class App {
    public static void main(String[] args) {
        // Veiculo v = new Veiculo("aaaa", "bbbbb", false);

        // v.ignicao();

        // System.out.println(v.situacao);

        Motocicleta cg = new Motocicleta("aaa", "sswd", false);
        cg.ignicao();

        AutoPasseio ap = new AutoPasseio("null", "null", false, 3);
        ap.ignicao();

        Cancela cancela1 = new Cancela();

        cancela1.cobrar(ap);
        cancela1.cobrar(cg);



        Triangulo tri = new Triangulo();
        tri.nome = "Tri";
        tri.altura = 10;
        tri.base = 4;
        tri.ladoA = 5;
        tri.ladoB = 6;
        tri.ladoC = 5;

        Quadrado qua = new Quadrado();
        qua.lado = 5;
        qua.nome = "QUE";

        ConsumidorFigura consumidorFigura = new ConsumidorFigura();

        consumidorFigura.desenhar(qua);

    }
}
