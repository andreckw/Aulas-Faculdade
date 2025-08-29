package AtividadeIndustria;

public class SoundBar extends Produto implements ICalculaFrete {

    public SoundBar() {
        super(670, 8000, 1800);
    }

    @Override
    public float frete() {
        float fretePorPeso = this.calculaFretePeso();
        float fretePorMetro = this.calculaFreteVolume();

        return fretePorMetro + fretePorPeso;
    }
    
}
