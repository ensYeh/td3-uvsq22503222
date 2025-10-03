package fr.uvsq.cprog.collex;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Dns {
    private String cheminFichier;
    private List<DnsItem> items = new ArrayList<>();

    public Dns(String cheminFichier) throws IOException {
        this.cheminFichier = cheminFichier;
        List<String> lignes = Files.readAllLines(Paths.get(cheminFichier));
        for (String line : lignes) {
            String s = line.trim();
            if (s.isEmpty() || s.startsWith("#"))
                continue;
            String[] parts = s.split("\\s+");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Ligne invalide : " + line);
            }
            NomMachine nom = new NomMachine(parts[0]);
            AdresseIP ip = new AdresseIP(parts[1]);
            items.add(new DnsItem(nom, ip));
        }
    }

    public DnsItem getItem(AdresseIP ip) {
        for (DnsItem it : items) {
            if (it.getIp().toString().equals(ip.toString())) {
                return it;
            }
        }
        return null;
    }

    public DnsItem getItem(NomMachine nom) {
        for (DnsItem it : items) {
            if (it.getNom().getValeur().equals(nom.getValeur())) {
                return it;
            }
        }
        return null;
    }

    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> resultat = new ArrayList<>();
        for (DnsItem it : items) {
            String fqdn = it.getNom().getValeur();
            if (fqdn.equals(domaine) || fqdn.endsWith("." + domaine)) {
                resultat.add(it);
            }
        }
        return resultat;
    }

    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
        if (getItem(ip) != null) {
            throw new IllegalArgumentException("Adresse IP déjà utilisée : " + ip);
        }
        if (getItem(nom) != null) {
            throw new IllegalArgumentException("Nom déjà utilisé : " + nom);
        }
        DnsItem item = new DnsItem(nom, ip);
        items.add(item);
        List<String> lignes = new ArrayList<>();
        for (DnsItem it : items) {
            lignes.add(it.toString());
        }
        Files.write(Paths.get(cheminFichier), lignes);
    }
}