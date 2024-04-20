package main.modeles.actions;

import main.modeles.entites.Bandit;

import java.awt.*;

public abstract class Action {
    public abstract Image getIcon();
    public abstract void apply(Bandit bandit);
    public abstract boolean canApply(Bandit bandit);
}
