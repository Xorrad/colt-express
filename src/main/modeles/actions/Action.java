package main.modeles.actions;

import main.modeles.entites.Bandit;

import java.awt.*;

public abstract class Action {
    protected Bandit bandit;

    public Action(Bandit bandit) {
        this.bandit = bandit;
    }

    public Bandit getBandit() {
        return bandit;
    }

    public abstract Image getIcon();
    public abstract void apply();
    public abstract boolean canApply();
}
