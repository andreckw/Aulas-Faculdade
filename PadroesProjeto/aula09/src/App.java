import classes.User;

public class App {
    public static void main(String[] args) throws Exception {
        User userOri = new User(10, "Teste1");

        User userClone = userOri.clone();

        System.out.println(userOri);
        System.out.println(userClone);
    }
}
