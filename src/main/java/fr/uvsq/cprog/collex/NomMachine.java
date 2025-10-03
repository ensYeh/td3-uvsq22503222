package fr.uvsq.cprog.collex;

public class NomMachine {
    private final String valeur;

    public NomMachine(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return valeur;
    }
}
