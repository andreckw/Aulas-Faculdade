package classes;

public abstract class Veiculo {
    private String modelo;
    private String cor;
    public boolean situacao = false;
    
    public Veiculo(String modelo, String cor, boolean situacao) {
        this.modelo = modelo;
        this.cor = cor;
        this.situacao = situacao;
    }

    public boolean ignicao() {
        this.situacao = !this.situacao;

        return this.situacao;
    }

    
    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public boolean isSituacao() {
        return situacao;
    }
    
    public abstract Integer getInfo();
}