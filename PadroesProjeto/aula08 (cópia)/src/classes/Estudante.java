package classes;

import java.util.ArrayList;
import java.util.List;

public class Estudante {
    
    private List<Observer> observers = new ArrayList<>();
    private String situacao;

    public Estudante(String situacao) {
        this.situacao = situacao;
        new Diploma(this);
        new ColecaoDeGrau(this);
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
        if (this.situacao == "Formado") {
            this.notifier();
        }
    }

    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    public void destach(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifier() {
        for (Observer observer : observers) {
            observer.processar();
        }
    }
}
