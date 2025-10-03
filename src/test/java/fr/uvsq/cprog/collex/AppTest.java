package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
}
