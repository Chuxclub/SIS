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
    private Door d4;
    private LockedDoor d5;
    private Door d6;

    private Room r1;
    private Room r2;
    private Room r3;
    private Room r4;

    private Player player;
    private Pass i1;



    @Before
    public void setUp()
    {
        //Création des pièces:
        r1 = new Room(ship,1,"room-test1");
        r2 = new Room(ship,2,"room-test2");
        r3 = new Room(ship,3,"room-test3");
        r4 = new Room(ship,4,"room-test4");

        //Création des portes:
        d1 = new Door("door1");
        d2 = new Door("door2");
        d3 = new LockedDoor("door3",PassType.A);
        d4 = new LockedDoor("door4",PassType.B);
        d5 = new LockedDoor("door5",PassType.C);
        d6 = new Door("door6");

        //Connexion des pièces entre elles:
        r1.addDoor(d1,r2);
        r1.addDoor(d3,r3);
        r1.addDoor(d6, r4);
        r2.addDoor(d2,r1);
        r3.addDoor(d4, r1);
        r4.addDoor(d5, r1);
        player = new Player(r1, ship);

        //Enrichissement des pièces:
        i1= new Pass("1","This is a pass",PassType.A);
        r1.getInventory().addItem(i1);
    }

    @After
    public void tearDown()
    {
    }

    //On tente de revenir dans la pièce précédente
    //alors qu'il n'y a pas de pièce précédente:
    @Test
    public void testBackInit()
    {
        player.back();
        assertEquals(r1, player.getRoom());
    }

    //On tente de revenir avec une porte normale:
    @Test
    public void testBackNormal()
    {
        player.go(d1);
        assertEquals(r2, player.getRoom());

        player.back();
        assertEquals(r1, player.getRoom());
    }

    //On tente de revenir avec une porte verrouillée:
    @Test
    public void testBackLocked()
    {
        player.go(d6);
        assertEquals(r4, player.getRoom());

        player.back();
        assertEquals(r4, player.getRoom());
    }

    @Test
    public void testGo()
    {
        Room r = player.getRoom();
        //On va dans un autre piece:
        player.go(d1);

        //Le joueur doit se souvenir de la pièce précédente et avoir changé de pièce:
        assertEquals(r2, player.getRoom());
        assertEquals(r1, player.getPreviousRoom());

        //La pièce précédente ne doit plus contenir le joueur:
        assertFalse(r.hasActor(player.getName()));

        //La pièce où est allé le joueur doit contenir le joueur:
        assertTrue(r2.hasActor(player.getName()));
    }


    @Test
    public void testMouvementPlayerGoThrougtlockdoor()
    {
        //on veux allez dans une piece mais on ne peut pas
        player.go(d3);
        assertEquals(r1,player.getRoom());

    }


    //test soin et mort plus soin apres la mort  verification hp max et pas au dela

    @Test
    public void testHpdown()
    {
        int i=player.getHp();
        System.out.print(player.getHp()+"\n");
        player.isAttacked(player);
        System.out.print(player.getHp());
        assertNotEquals(i,player.getHp());

    }
    @Test
    public void testHpHeal()
    {
        player.isAttacked(player);//vie perdu 20
        int i=player.getHp();
        player.isHealed(10);
        assertNotEquals(i,player.getHp());

    }

    @Test
    public void testHpOverHeal()
    {
        player.isAttacked(player);//vie perdu 20
        player.isHealed(200);
        assertTrue(player.getDEFAULT_HP_MAX()>=player.getHp());

    }
    @Test
    public void testHpDead()
    {
        System.out.print(player.getHp());
        while(player.getHp()>0)
        {
            player.isAttacked(player);//vie perdu 20
        }
        assertTrue(player.isDead());

    }

    @Test
    public void testHpDeadOverHeal()
    {
        System.out.print(player.getHp());
        while(player.getHp()>0)
        {
            player.isAttacked(player);//vie perdu 20
        }
        player.isHealed(10);
        assertTrue(player.isDead());

    }


    //test prendre un objet d'une piece puis le laisser de nouveau dedans

    @Test
    public void testItempickup()
    {
        assertFalse(player.getRoom().getInventory().isEmpty());
        assertTrue(player.getInventory().isEmpty());
        player.take(i1);
        assertFalse(player.getInventory().isEmpty());
        assertTrue(player.getRoom().getInventory().isEmpty());

    }

    @Test
    public void testItemDrop()
    {
        player.take(i1);
        player.drop(i1);
        assertFalse(player.getRoom().getInventory().isEmpty());
        assertTrue(player.getInventory().isEmpty());
    }
    @Test
    public void testItemUse()
    {
        player.take(i1);
        player.use(i1,d3);
        player.go(d3);
        assertEquals(r3,player.getRoom());
    }

}
