package classes;

public abstract class Observer {
    protected Estudante estudante;

    public Observer(Estudante estudante) {
        this.estudante = estudante;
        this.estudante.attach(this);
    }

    public void processar() {}

}
