package interfaces;

public interface ProcessadorPagamento {
    public void processadorPagamento(double valor);
    public boolean validarCartao(String cartao);
}
