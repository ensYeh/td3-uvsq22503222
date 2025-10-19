package fr.uvsq.cprog.collex;

public class ChercherNomCommande implements Commande {
    private final Dns dns;
    private final AdresseIP ip;

    public ChercherNomCommande(Dns dns, AdresseIP ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public String execute() {
        DnsItem it = dns.getItem(ip);
        return (it == null) ? "NOT FOUND" : it.getNom().getValeur();
    }

}
