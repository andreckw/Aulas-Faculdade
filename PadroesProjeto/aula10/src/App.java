import classes.Calculadora;
import classes.operacoes.Divisao;
import classes.operacoes.Multiplicacao;
import classes.operacoes.Porcent;
import classes.operacoes.Somar;
import classes.operacoes.Substratacao;

public class App {
    public static void main(String[] args) throws Exception {
        
        Calculadora calculadora = new Calculadora(new Somar());

        System.out.println(calculadora.calcular(20, 20)); // 40

        calculadora.setStragtegy(new Substratacao());
        System.out.println(calculadora.calcular(20, 20)); // 0

        calculadora.setStragtegy(new Multiplicacao());
        System.out.println(calculadora.calcular(20, 20)); // 400

        calculadora.setStragtegy(new Divisao());
        System.out.println(calculadora.calcular(20, 20)); // 1

        calculadora.setStragtegy(new Porcent());
        System.out.println(calculadora.calcular(50, 100)); // 1
    }
}
