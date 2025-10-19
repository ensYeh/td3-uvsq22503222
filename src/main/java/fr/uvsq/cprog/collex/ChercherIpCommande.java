package fr.uvsq.cprog.collex;

public class ChercherIpCommande implements Commande {
    private final Dns dns;
    private final NomMachine nom;

    public ChercherIpCommande(Dns dns, NomMachine nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public String execute() {
        DnsItem it = dns.getItem(nom);
        return (it == null) ? "NOT FOUND" : it.getIp().toString();
    }
}
