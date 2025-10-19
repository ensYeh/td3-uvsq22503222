package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsApp {
    private final DnsTUI tui;

    public DnsApp(Dns dns) {
        this.tui = new DnsTUI(dns);
    }

    public void run(Scanner in) {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            try {
                Commande cmd = tui.nextCommande(line);
                String res = cmd.execute();
                tui.affiche(res);
                if ("Au revoir".equals(res)) {
                    break;
                }
            } catch (Exception e) {
                tui.affiche("ERREUR: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: java ... DnsApp <fichier_db>");
            return;
        }
        Dns dns = new Dns(args[0]);
        DnsApp app = new DnsApp(dns);
        try (Scanner sc = new Scanner(System.in)) {
            app.run(sc);
        }
    }
}
