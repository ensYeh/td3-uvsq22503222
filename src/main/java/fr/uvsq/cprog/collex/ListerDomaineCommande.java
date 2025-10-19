package fr.uvsq.cprog.collex;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListerDomaineCommande implements Commande {
    private final Dns dns;
    private final String domaine;
    private final boolean sortByIp;

    public ListerDomaineCommande(Dns dns, String domaine, boolean sortByIp) {
        this.dns = dns;
        this.domaine = domaine;
        this.sortByIp = sortByIp;
    }

    @Override
    public String execute() {
        List<DnsItem> list = dns.getItems(domaine);
        if (sortByIp)
            list.sort(Comparator.comparing(it -> it.getIp().toString()));
        else
            list.sort(Comparator.comparing(it -> it.getNom().getValeur()));
        return list.stream().map(DnsItem::toString).collect(Collectors.joining("\n"));
    }
}
