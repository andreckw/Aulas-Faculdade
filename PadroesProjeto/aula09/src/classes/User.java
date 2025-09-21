package classes;

public class User implements Prototype {

    private int id;
    private String nome;


    public User(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    private User(User user) {
        this.id = user.id;
        this.nome = user.nome;
    }


    @Override
    public User clone() {
        return new User(this);
    }

    @Override
    public String toString() {
        return "("+this.hashCode()+") \n Nome: "+this.nome;
    }
}