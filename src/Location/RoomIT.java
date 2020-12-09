package Location;

import Characters.Player;
import Doors.Door;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomIT
{
    private Ship ship = null;
    private Door d1;
    private Room r1;
    private Room r2;
    private Player a1;

    @Before
    public void setUp()
    {
        d1 = new Door("door1");
        r1 = new Room(ship,1,"room-test1");
        r2 = new Room(ship,2,"room-test2");
        a1= new Player(r1, ship);


    }

    @After
    public void tearDown()
    {
    }

    //test mouvements entre le pieces
    //test Boîte Noire
    @Test
    public void TestAddActor()
    {
        r1.addActor(a1);
        assertEquals(r1.getActor("me"),a1);
        assertTrue(r1.hasActor("me"));
    }

    //test Boîte Blanche
    @Test
    public void TestRemoveActor()
    {
        r1.addActor(a1);
        r1.removeActor("me");
        assertNotEquals(r1.getActor("me"),a1);

    }

    //test Boîte Blanche
    @Test
    public void TestAddDoor()
    {
        r1.addDoor(d1,r1);
        assertEquals(r1.getDoor("door1"),d1);
    }

    //test Boîte Noire
    @Test
    public void TestRoomActorUseDoor()
    {
        r1.addActor(a1);
        r1.addDoor(d1,r1);
        r1.useDoor(a1,d1);
        assertTrue(r1.hasActor("me"));


    }
}
