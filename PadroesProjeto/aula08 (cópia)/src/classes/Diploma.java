package classes;

public class Diploma extends Observer {

    public Diploma(Estudante estudante) {
        super(estudante);
    }

    @Override
    public void processar() {
        System.out.println("Ganhou diploma");
    }

}
