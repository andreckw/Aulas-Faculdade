package AtividadeIndustria;

public class MiniPc extends Produto implements ICalculaFrete {

    public MiniPc() {
        super(500, 200, 5000);
    }

    @Override
    public float frete() {
        float fretePorPeso = this.calculaFretePeso();
        float fretePorMetro = this.calculaFreteVolume();

        return fretePorMetro + fretePorPeso;
    }
    
}
