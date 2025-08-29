package AtividadeIndustria;

public class SuperServidor extends Produto implements ICalculaFrete {

    public SuperServidor() {
        super(3800, 120000, 30000);
    }

    @Override
    public float frete() {
        float fretePorPeso = this.calculaFretePeso();
        float fretePorMetro = this.calculaFreteVolume();
        float fretePorPreco = (float) (this.preco * 0.30);

        return fretePorMetro + fretePorPeso + fretePorPreco;
    }
    
}
