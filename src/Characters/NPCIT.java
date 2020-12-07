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
    private Player player;
    private NPC a1;
    private Ship ship = null;
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
        a1 = new NPC("a1", "An NPC",false, true, list, r1);
        player = new Player(r1, ship);
    }

    @After
    public void tearDown() {
    }

    //Test changement de piéce et vérification que le npc connaît la piece précédente
    // (cas où tout doit fonctionner):
    @Test
    public void testChangeRoom() {

        //On va dans la pièce 2:
        a1.changeRoom(r2);

        //La pièce 1 ne doit plus contenir le joueur:
        assertFalse(r1.hasActor(a1.getName()));

        //La pièce 2 doit contenir le joueur:
        assertTrue(r2.hasActor(a1.getName()));

        //L'acteur doit savoir qu'il est dans la pièce 2:
        assertEquals(r2, a1.getRoom());

        //L'acteur doit savoir que sa pièce précédente est la pièce 1:
        assertEquals(r1, a1.getPreviousRoom());
    }

    /* ======================================================== */
    /* ======================== COMBAT ======================== */
    /* ======================================================== */

    @Test
    public void testCombat()
    {
        int a1Hp = a1.getHp();
        int playerHp = player.getHp();

        //Le joueur attaque un NPC allié... Les vies du NPC doivent descendre mais pas ceux
        //du joueur car le NPC ne réplique pas encore. Le NPC doit être devenu neutre:
        player.attack(a1);
        assertNotEquals(a1Hp, a1.getHp());
        assertFalse(a1.isAlly());
        assertEquals(playerHp, player.getHp());

        //Le NPC doit avoir perdu des vies. Il est devenu hostile. Le joueur doit avoir perdu
        //des vies car le NPC réplique:
        a1Hp = a1.getHp();
        playerHp = player.getHp();

        player.attack(a1);
        assertNotEquals(a1Hp, a1.getHp());
        assertTrue(a1.isHostile());
        assertNotEquals(playerHp, player.getHp());

        //Quand le NPC est mort ce-dernier ne perd plus de vies, le joueur non plus:
        while(!(a1.isDead()))
            player.attack(a1);

        a1Hp = a1.getHp();
        playerHp = player.getHp();

        player.attack(a1);
        assertEquals(a1Hp, a1.getHp());
        assertEquals(playerHp, player.getHp());
    }

    /* ======================================================== */
    /* ========================= HEAL ========================= */
    /* ======================================================== */

    @Test
    public void testHeal()
    {
        //On fait perdre des hp au NPC:
        a1.isAttacked(player);
        int i = a1.getHp();

        //Si on soigne de 10 alors la vie du NPC a augmenté de 10:
        a1.isHealed(10);
        assertEquals(i + 10,a1.getHp());
    }

    @Test
    public void testExcessHeal()
    {
        //On fait perdre des hp au NPC:
        a1.isAttacked(player);

        //Si on soigne au-delà de la vie maximum possible, le nombre d'hp
        //du npc n'a pas augmenté au-delà de sa vie maximum possible:
        a1.isHealed(a1.getDEFAULT_HP_MAX() + 10);
        assertEquals(a1.getDEFAULT_HP_MAX(), a1.getHp());

    }

    @Test
    public void testHealDead()
    {
        while(!(a1.isDead()))
            a1.isAttacked(a1);

        assertTrue(a1.isDead());
        assertEquals(0, a1.getHp());
    }
}
