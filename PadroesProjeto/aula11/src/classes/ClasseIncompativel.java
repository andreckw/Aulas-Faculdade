package classes;

public class ClasseIncompativel {
    
    public void efetuarPagamento(double valor, String moeda) {
        System.out.println("Pagamento: " + moeda + " " + valor);
    }

    public boolean verificarCartaoCredito(String cartao) {
        return cartao != null && cartao.length() == 16;
    }

}
