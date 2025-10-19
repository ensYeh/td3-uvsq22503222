package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testFromString() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals(192, ip.getO1());
        assertEquals(168, ip.getO2());
        assertEquals(0, ip.getO3());
        assertEquals(1, ip.getO4());
        assertEquals("192.168.0.1", ip.toString());
    }

    @Test
    public void testFromInts() {
        AdresseIP ip = new AdresseIP(10, 0, 0, 5);
        assertEquals("10.0.0.5", ip.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFormat() {
        new AdresseIP("192.168.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRange() {
        new AdresseIP("300.1.1.1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNonNumeric() {
        new AdresseIP("a.b.c.d");
    }

    private File makeDb(String content) throws Exception {
        File f = File.createTempFile("dnsdb", ".txt");
        try (FileWriter w = new FileWriter(f)) {
            w.write(content);
        }
        return f;
    }

    @Test
    public void testResolveByName() throws Exception {
        File db = makeDb("www.example.com 1.2.3.4\n");
        Dns dns = new Dns(db.getAbsolutePath());

        DnsTUI tui = new DnsTUI(dns);
        Commande cmd = tui.nextCommande("www.example.com");
        String out = cmd.execute();
        assertEquals("1.2.3.4", out);
    }

    @Test
    public void testResolveByIp() throws Exception {
        File db = makeDb("www.example.com 1.2.3.4\n");
        Dns dns = new Dns(db.getAbsolutePath());

        DnsTUI tui = new DnsTUI(dns);
        Commande cmd = tui.nextCommande("1.2.3.4");
        String out = cmd.execute();
        assertEquals("www.example.com", out);
    }

    @Test
    public void testListDomain() throws Exception {
        File db = makeDb("a.example.com 1.1.1.1\nb.example.com 2.2.2.2\n");
        Dns dns = new Dns(db.getAbsolutePath());

        DnsTUI tui = new DnsTUI(dns);
        Commande cmd = tui.nextCommande("ls example.com");
        String out = cmd.execute();
        System.out.println("OUT = [" + out + "]");
        assertTrue(out.contains("a.example.com 1.1.1.1"));
        assertTrue(out.contains("b.example.com 2.2.2.2"));
    }

    @Test
    public void testQuit() throws Exception {
        File db = makeDb("");
        Dns dns = new Dns(db.getAbsolutePath());

        DnsTUI tui = new DnsTUI(dns);
        Commande cmd = tui.nextCommande("exit");
        String out = cmd.execute();
        assertEquals("Au revoir", out);
    }
}
