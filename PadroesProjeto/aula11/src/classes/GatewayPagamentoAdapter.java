package classes;

import interfaces.ProcessadorPagamento;

public class GatewayPagamentoAdapter implements ProcessadorPagamento {

    protected ClasseIncompativel gatewayLegado;

    public GatewayPagamentoAdapter(ClasseIncompativel gateway) {
        this.gatewayLegado = gateway;
    }

    @Override
    public void processadorPagamento(double valor) {
        gatewayLegado.efetuarPagamento(valor, "R$");
    }

    @Override
    public boolean validarCartao(String cartao) {
        return gatewayLegado.verificarCartaoCredito(cartao);
    }
    
}
