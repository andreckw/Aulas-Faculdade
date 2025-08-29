package AtividadeIndustria;

public abstract class Produto {
    protected float peso;
    protected float volume;
    protected float preco;

    Produto(float peso, float volume, float preco) {
        this.peso = peso;
        this.volume = volume;
        this.preco = preco;
    }


    protected float calculaFretePeso() {
        return (float) ((this.peso / 1000) * 0.80);
    }

    protected float calculaFreteVolume() {
        return (this.volume / 1000000) * 1;
    }
}
