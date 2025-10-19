package fr.uvsq.cprog.collex;

public class AjouterItemCommande implements Commande {
    private final Dns dns;
    private final AdresseIP ip;
    private final NomMachine nom;

    public AjouterItemCommande(Dns dns, AdresseIP ip, NomMachine nom) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public String execute() throws Exception {
        dns.addItem(ip, nom);
        return "OK";
    }
}
