package fr.uvsq.cprog.collex;

public class DnsTUI {
    private final Dns dns;

    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    public Commande nextCommande(String saisie) {
        if (saisie == null || saisie.trim().isEmpty()) {
            throw new IllegalArgumentException("entrée vide");
        }
        String s = saisie.trim();

        if (s.startsWith("ls ")) {
            String[] parts = s.split("\\s+");
            if (parts.length == 2) {
                return new ListerDomaineCommande(dns, parts[1], false);
            } else if (parts.length == 3 && "-a".equals(parts[1])) {
                return new ListerDomaineCommande(dns, parts[2], true);
            } else {
                throw new IllegalArgumentException("usage: ls [-a] <domaine>");
            }
        }

        if (s.startsWith("add ")) {
            String[] parts = s.split("\\s+");
            if (parts.length == 3) {
                return new AjouterItemCommande(dns, new AdresseIP(parts[2]), new NomMachine(parts[1]));
            } else {
                throw new IllegalArgumentException("usage: add <nom> <ip>");
            }
        }

        if (s.equalsIgnoreCase("exit")) {
            return new QuitterCommande();
        }

        if (s.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            return new ChercherNomCommande(dns, new AdresseIP(s));
        } else {
            return new ChercherIpCommande(dns, new NomMachine(s));
        }
    }

    public void affiche(String resultat) {
        if (resultat != null && !resultat.isEmpty()) {
            System.out.println(resultat);
        }
    }
}
