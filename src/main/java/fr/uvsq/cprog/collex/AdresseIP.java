package fr.uvsq.cprog.collex;

/** Représente une adresse IPv4 simple avec 4 octets entiers. */
public class AdresseIP {
    private final int o1;
    private final int o2;
    private final int o3;
    private final int o4;

    public AdresseIP(int o1, int o2, int o3, int o4) {
        verifier(o1);
        verifier(o2);
        verifier(o3);
        verifier(o4);
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
    }

    /** Construit une AdresseIP depuis une chaîne "a.b.c.d". */
    public AdresseIP(String valeur) {
        String[] parts = valeur.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Adresse IP invalide : " + valeur);
        }
        try {
            o1 = Integer.parseInt(parts[0]);
            o2 = Integer.parseInt(parts[1]);
            o3 = Integer.parseInt(parts[2]);
            o4 = Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Octet non numérique : " + valeur);
        }
        verifier(o1);
        verifier(o2);
        verifier(o3);
        verifier(o4);
    }

    private static void verifier(int v) {
        if (v < 0 || v > 255) {
            throw new IllegalArgumentException("Octet hors plage : " + v);
        }
    }

    public int getO1() {
        return o1;
    }

    public int getO2() {
        return o2;
    }

    public int getO3() {
        return o3;
    }

    public int getO4() {
        return o4;
    }

    @Override
    public String toString() {
        return o1 + "." + o2 + "." + o3 + "." + o4;
    }
}
