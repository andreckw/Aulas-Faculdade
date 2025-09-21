package classes;

public class ColecaoDeGrau extends Observer {

    public ColecaoDeGrau(Estudante estudante) {
        super(estudante);
    }

    @Override
    public void processar() {
        System.out.println("Tem colecao de grau");
    }
}
