package fr.uvsq.cprog.collex;

public class DnsItem {
  private final NomMachine nom;
  private final AdresseIP ip;

  public DnsItem(NomMachine nom, AdresseIP ip) {
    this.nom = nom;
    this.ip = ip;
  }

  public NomMachine getNom() {
    return nom;
  }

  public AdresseIP getIp() {
    return ip;
  }

  @Override
  public String toString() {
    return nom + " : " + ip;
  }
}

