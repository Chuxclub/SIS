package Characters;

import Doors.Door;
import Doors.LockedDoor;
import Items.Item;
import Items.Pass;
import Items.PassType;
import Location.Room;
import Location.Ship;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerIT
{
    private Ship ship;
    private Door d1;
    private Door d2;
    private LockedDoor d3;
    private Room r1;
    private Room r2;
    private Room r3;
    private Player a1;
    private Pass i1;



    @Before
    public void setUp()
    {
        d1 = new Door("door1");
        d2 = new Door("door2");
        d3= new LockedDoor("door3",PassType.A);
        r1 = new Room(ship,1,"room-test1");
        r2 = new Room(ship,2,"room-test2");
        r3 = new Room(ship,3,"room-test3");
        i1= new Pass("1","c'est une balle",PassType.A);
        a1= new Player(r1, ship);
        r1.addDoor(d1,r2);
        r2.addDoor(d2,r1);
        r1.addDoor(d3,r3);
        r1.getInventory().addItem(i1);
    }

    @After
    public void tearDown()
    {
    }

    //test mouvements entre le pieces
    @Test
    public void testMouvementPlayerBackinit()
    {

        //on va ensuite revenir dans l'ancienne
        a1.back();
        assertEquals(r1, a1.getRoom());


    }

    @Test
    public void testMouvementPlayerGo()
    {


        //test changement de piéce et verification qu'il connaisse la piece précedente (cas ou tout doit fonctionner):

        //on va dans un autre piece
        a1.go(d1);
        assertEquals(r2,a1.getRoom());
        assertNotSame(a1.getRoom(),r1);


    }
    @Test
    public void testMouvementPlayerBack()
    {

        //on va ensuite revenir dans l'ancienne
        a1.go(d1);
        a1.back();
        assertEquals(r1, a1.getRoom());
        assertNotSame(a1.getRoom(),r2);


    }

    @Test
    public void testMouvementPlayerGoThrougtlockdoor()
    {

        //on veux allez dans une piece mais on ne peut pas
        a1.go(d3);
        assertEquals(r1,a1.getRoom());

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


    //test prendre un objet d'une piece puis le laisser de nouveau dedans

    @Test
    public void testItempickup()
    {
        assertFalse(a1.getRoom().getInventory().isEmpty());
        assertTrue(a1.getInventory().isEmpty());
        a1.take(i1);
        assertFalse(a1.getInventory().isEmpty());
        assertTrue(a1.getRoom().getInventory().isEmpty());

    }

    @Test
    public void testItemDrop()
    {
        a1.take(i1);
        a1.drop(i1);
        assertFalse(a1.getRoom().getInventory().isEmpty());
        assertTrue(a1.getInventory().isEmpty());
    }
    @Test
    public void testItemUse()
    {
        a1.take(i1);
        a1.use(i1,d3);
        a1.go(d3);
        assertEquals(r3,a1.getRoom());
    }

}
