package Commands;

import Characters.NPC;
import Characters.Player;
import Doors.Door;
import Doors.LockedDoor;
import Items.*;
import Location.Room;
import Location.Ship;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CommandIT {

    private Player player;
    private NPC npc;

    private Room room1;
    private Room room2;
    private Room room3;

    private Door door1to2;
    private Door door2to1;
    private LockedDoor door1to3;
    private Door door3to1;

    private Ship ship;
    private List<TakableItem> npc_inventory;
    private Item item;
    private TakableItem tk_item;
    private List<String> args;


    @Before
    public void setUp() {
        room1 = new Room(ship, 1, "room-test1");
        room2 = new Room(ship, 2, "room-test2");
        room3 = new Room(ship, 3, "room-test2");

        door1to2 = new Door("door1to2");
        door2to1 = new Door("door2to1");
        door1to3 = new LockedDoor("door1to3", PassType.A);
        door3to1 = new Door("door1to3");

        room1.addDoor(door1to2, room2);
        room2.addDoor(door2to1, room1);
        room1.addDoor(door1to3, room3);
        room3.addDoor(door3to1, room1);

        npc_inventory = new ArrayList<>();
        npc = new NPC("npc", "an npc", false, true, npc_inventory, room1);

        player = new Player(room1, ship);
        tk_item = new Artefact("statue", "a statue");
        player.getInventory().addItem(tk_item);

        args = new ArrayList<>();
    }

    @After
    public void tearDown() {
    }

    /* ============================================================= */
    /* ======================== Wrong Verb ========================= */
    /* ============================================================= */

    @Test
    public void testWrongVerb() {
        try {
            Command cmd = new Command(player, "blablaincoherentverb", null);
            cmd.exec();
            fail();
        } catch (UnknownVerb e) {
            System.out.println("Enter help for valid verbs");
        }
    }

    /* ============================================================= */
    /* =========================== Back ============================ */
    /* ============================================================= */

    @Test
    public void testBackThroughNormalDoor() {
        player.go(door1to2);
        try {
            Command cmd = new Command(player, "back", null);
            cmd.exec();
            assertEquals(room1, player.getRoom());
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBackThroughLockedDoor() {
        player = new Player(room3, ship);

        //The door3to1 isn't a locked door, the player can go to room1.
        //His previous room is then room3:
        player.go(door3to1);

        try {
            Command cmd = new Command(player, "back", args);
            cmd.exec();
            assertNotEquals(room3, player.getRoom());
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    /* ======================================================== */
    /* ========================= DROP ========================= */
    /* ======================================================== */

    @Test
    public void testDropGoodArg() {
        try {
            Command cmd = new Command(player, "drop", Collections.singletonList("statue"));
            cmd.exec();
            assertNull(player.getInventory().getItem("statue"));
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDropWrongArg() {
        try {
            Command cmd = new Command(player, "drop", Collections.singletonList("blob"));
            cmd.exec();
            assertEquals(tk_item, player.getInventory().getItem("statue"));
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDropNoArg() {
        try {
            Command cmd = new Command(player, "drop", args);
            cmd.exec();
            assertEquals(tk_item, player.getInventory().getItem("statue"));
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    /* ======================================================== */
    /* ========================= GIVE ========================= */
    /* ======================================================== */

    @Test
    public void testGiveGoodArg() {
        args.add(0, npc.getName());
        args.add(1, tk_item.getTag());

        try {
            Command cmd = new Command(player, "give", args);
            cmd.exec();
            assertEquals(tk_item, player.getInventory().getItem("statue"));
            assertEquals(tk_item.getTag(), npc.getInventory().getItem("statue").getTag());
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGiveWrongArg() {
        args.add(0, npc.getName());
        args.add(1, "blob");

        try {
            Command cmd = new Command(player, "give", args);
            cmd.exec();
            assertEquals(tk_item, player.getInventory().getItem("statue"));
            assertNull(npc.getInventory().getItem("statue"));
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGiveNoArg() {
        try {
            Command cmd = new Command(player, "give", args);
            cmd.exec();
            assertEquals(tk_item, player.getInventory().getItem("statue"));
            assertNull(npc.getInventory().getItem("statue"));
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGiveMissingOneArg()
    {
        args.add(0, npc.getName());
        try {
            Command cmd = new Command(player, "give", args);
            cmd.exec();
            assertEquals(tk_item, player.getInventory().getItem("statue"));
            assertNull(npc.getInventory().getItem("statue"));
        } catch (UnknownVerb e) {
            e.printStackTrace();
        }
    }

    /* ======================================================== */
    /* ========================== GO ========================== */
    /* ======================================================== */

    @Test
    public void testGo() {

    }

    /* ======================================================== */
    /* ========================= TAKE ========================= */
    /* ======================================================== */

    @Test
    public void testTake() {

    }

    /* ======================================================== */
    /* ========================== USE ========================= */
    /* ======================================================== */

    @Test
    public void testUse() {

    }
}

