import classes.ClasseIncompativel;
import classes.GatewayPagamentoAdapter;
import classes.GatewayPagamentoLegado;
import interfaces.ProcessadorPagamento;

public class App {
    public static void main(String[] args) throws Exception {
        ProcessadorPagamento processador = new GatewayPagamentoAdapter(new ClasseIncompativel());

        String cartao = "12345678912345678";

        if (processador.validarCartao(cartao)) {
            processador.processadorPagamento(15);
        }
    }
}
