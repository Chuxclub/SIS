package Characters;

import Doors.Door;
import Doors.LockedDoor;
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
    private final Ship ship = null;

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
    private Pass passA;



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
        passA = new Pass("passA","This is a pass",PassType.A);
        r1.getInventory().addItem(passA);
    }

    @After
    public void tearDown()
    {
    }

    /* ====================================================== */
    /* ======================= BACK ========================= */
    /* ====================================================== */

    //On tente de revenir dans la pièce précédente
    //alors qu'il n'y a pas de pièce précédente:
    //test Boîte Noire
    @Test
    public void testBackInit()
    {
        player.back();
        assertEquals(r1, player.getRoom());
    }

    //On tente de revenir avec une porte normale:
    //test Boîte Noire
    @Test
    public void testBackNormal()
    {
        player.go(d1);
        assertEquals(r2, player.getRoom());

        player.back();
        assertEquals(r1, player.getRoom());
    }

    //On tente de revenir avec une porte verrouillée:
    //test Boîte Noire
    @Test
    public void testBackLocked()
    {
        player.go(d6);
        assertEquals(r4, player.getRoom());

        player.back();
        assertEquals(r4, player.getRoom());
    }


    /* ====================================================== */
    /* ========================= GO ========================= */
    /* ====================================================== */

    //test Boîte Blanche
    @Test
    public void testGoNormal()
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


    //test Boîte Noire
    @Test
    public void testGoLocked()
    {
        //On veut aller dans une piece mais on ne peut pas:
        player.go(d3);
        assertEquals(r1,player.getRoom());
    }


    /* ======================================================== */
    /* ======================== COMBAT ======================== */
    /* ======================================================== */

    //test Boîte Blanche
    @Test
    public void testCombat()
    {
        int i=player.getHp();
        System.out.print(player.getHp()+"\n");
        player.isAttacked(player);
        System.out.print(player.getHp());
        assertNotEquals(i,player.getHp());

    }

    /* ======================================================== */
    /* ========================= HEAL ========================= */
    /* ======================================================== */

    //test Boîte Noire
    @Test
    public void testHeal()
    {
        player.isAttacked(player);//vie perdu 20
        int i=player.getHp();
        player.isHealed(10);
        assertNotEquals(i,player.getHp());

    }

    //test Boîte Blanche
    @Test
    public void testExcessHeal()
    {
        player.isAttacked(player);//vie perdu 20
        player.isHealed(200);
        assertTrue(player.getDEFAULT_HP_MAX()>=player.getHp());

    }


    /* ======================================================== */
    /* ======================= TAKE/DROP ====================== */
    /* ======================================================== */

    //test Boîte Blanche
    @Test
    public void testItemPickup()
    {
        assertFalse(player.getRoom().getInventory().isEmpty());
        assertTrue(player.getInventory().isEmpty());
        player.take(passA);
        assertFalse(player.getInventory().isEmpty());
        assertTrue(player.getRoom().getInventory().isEmpty());
    }

    //test Boîte Blanche
    @Test
    public void testItemDrop()
    {
        player.take(passA);
        player.drop(passA);
        assertFalse(player.getRoom().getInventory().isEmpty());
        assertTrue(player.getInventory().isEmpty());
    }


    /* ======================================================== */
    /* ========================== USE ========================= */
    /* ======================================================== */

    //test Boîte Blanche
    @Test
    public void testUsePassOnLocked()
    {
        player.take(passA);
        player.use(passA,d3);
        player.go(d3);
        assertEquals(r3,player.getRoom());
    }

}
