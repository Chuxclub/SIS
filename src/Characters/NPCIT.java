package Characters;

import Doors.Door;
import Doors.LockedDoor;
import Items.Item;
import Items.Pass;
import Items.PassType;
import Items.TakableItem;
import Location.Room;
import Location.Ship;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class NPCIT {
    private NPC a1;
    private Ship ship;
    private Door d1;
    private Door d2;
    private LockedDoor d3;
    private Room r1;
    private Room r2;
    private Room r3;
    private Pass i1;
    private List<TakableItem> list;


    @Before
    public void setUp() {
        d1 = new Door("door1");
        d2 = new Door("door2");
        d3 = new LockedDoor("door3", PassType.A);
        r1 = new Room(ship, 1, "room-test1");
        r2 = new Room(ship, 2, "room-test2");
        r3 = new Room(ship, 3, "room-test3");
        i1 = new Pass("1", "c'est une balle", PassType.A);
        r1.addDoor(d1, r2);
        r2.addDoor(d2, r1);
        r1.addDoor(d3, r3);
        r1.getInventory().addItem(i1);
        list = new ArrayList<>();
        a1 = new NPC("test", "An NPC",false, false, list, r1);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testChangeRoom() {


        //test changement de piéce et verification qu'il connaisse la piece précedente (cas ou tout doit fonctionner):

        //on va dans un autre piece
        a1.changeRoom(r2);
        assertFalse(r1.hasActor("test"));
        assertEquals(r2, a1.getRoom());
        assertNotSame(a1.getRoom(), r1);

    }




    //test soin et mort plus soin apres la mort  verification hp max et pas au dela

    @Test
    public void testHpdown()
    {
        int i=a1.getHp();
        System.out.print(a1.getHp()+"\n");
        a1.isAttacked(a1);
        System.out.print(a1.getHp());
        assertNotEquals(i,a1.getHp());

    }
    @Test
    public void testHpHeal()
    {
        a1.isAttacked(a1);//vie perdu 20
        int i=a1.getHp();
        a1.isHealed(10);
        assertNotEquals(i,a1.getHp());

    }
    @Test
    public void testHpOverHeal()
    {
        a1.isAttacked(a1);//vie perdu 20
        a1.isHealed(200);
        assertTrue(a1.getDEFAULT_HP_MAX()>=a1.getHp());

    }
    @Test
    public void testHpDead()
    {
        System.out.print(a1.getHp());
        while(a1.getHp()>0)
        {
            a1.isAttacked(a1);//vie perdu 20
        }
        assertTrue(a1.isDead());

    }

    @Test
    public void testHpDeadOverHeal()
    {
        System.out.print(a1.getHp());
        while(a1.getHp()>0)
        {
            a1.isAttacked(a1);//vie perdu 20
        }
        a1.isHealed(10);
        assertTrue(a1.isDead());

    }


}
