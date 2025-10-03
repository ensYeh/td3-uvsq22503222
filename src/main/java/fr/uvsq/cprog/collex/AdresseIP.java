package fr.uvsq.cprog.collex;

public class AdresseIP {
  private final String valeur;

  public AdresseIP(String valeur) {
    if (!valeur.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
      throw new IllegalArgumentException("Adresse IP invalide : " + valeur);
    }
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

