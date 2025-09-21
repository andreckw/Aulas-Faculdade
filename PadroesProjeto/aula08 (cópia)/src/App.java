import classes.Estudante;

public class App {
    public static void main(String[] args) throws Exception {
        Estudante estudante = new Estudante("Nao");

        estudante.setSituacao("Formado");
        estudante.setSituacao("Nao");
    }
}
