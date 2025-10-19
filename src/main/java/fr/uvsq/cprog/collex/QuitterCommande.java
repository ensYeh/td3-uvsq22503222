package fr.uvsq.cprog.collex;

public class QuitterCommande implements Commande {
    @Override
    public String execute() {
        return "Au revoir";
    }
}
