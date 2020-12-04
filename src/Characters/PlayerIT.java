package Characters;

import Doors.Door;
import Items.Item;
import Location.Room;
import Location.Ship;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerIT
{
    private Ship ship;
    private Door d1;
    private Door d2;
    private Room r1;
    private Room r2;
    private Player a1;
    private Player a2;
    private Item i1;


    @Before
    public void setUp()
    {
        d1 = new Door("door1");
        d2 = new Door("door2");
        r1 = new Room(ship,1,"room-test1");
        r2 = new Room(ship,2,"room-test2");
        a1= new Player(r1);
        r1.addDoor(d1,r2);
        r2.addDoor(d2,r1);


    }

    @After
    public void tearDown()
    {
    }

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
        assertEquals(false,a1.getRoom()==r1);


    }
    @Test
    public void testMouvementPlayerBack()
    {

        //on va ensuite revenir dans l'ancienne
        a1.go(d1);
        a1.back();
        assertEquals(r1, a1.getRoom());
        assertEquals(false,a1.getRoom()==r2);


    }

}
