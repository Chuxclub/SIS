package Containers;


import Items.Pass;
import Items.PassType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class InvertoryIT {
    private Inventory inv;
    private Pass i1;


    @Before
    public void setUp() {
        inv= new Inventory();
        i1 = new Pass("1", "c'est une balle", PassType.A);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestInvVide() {
        assertEquals(0,inv.getSize());
        assertTrue(inv.isEmpty());
    }

    @Test
    public void TestInvAdd() {
        inv.addItem(i1);
        assertEquals(1,inv.getSize());
        assertFalse(inv.isEmpty());
        assertEquals(inv.getItem("1"),i1);
    }

    @Test
    public void TestInvRemove() {
        inv.addItem(i1);
        inv.removeItem("1");
        assertEquals(0,inv.getSize());
        assertTrue(inv.isEmpty());
    }


}
