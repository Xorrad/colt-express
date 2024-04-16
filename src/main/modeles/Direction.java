package main.modeles;

public enum Direction {
    AVANT,
    ARRIERE,
    HAUT,
    BAS;

    public Direction oppose() {
        switch(this) {
            case AVANT: return ARRIERE;
            case ARRIERE: return AVANT;
            case HAUT: return BAS;
            //case BAS: return HAUT;
        }
        return HAUT;
    }
}
