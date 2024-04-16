package main.modeles;

import main.vues.Observer;

import java.util.ArrayList;

public abstract class Observable {
    private ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<Observer>();
    }

    public void ajouteObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers() {
        this.observers.forEach(o -> o.update());
    }
}
