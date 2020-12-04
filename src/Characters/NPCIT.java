package Characters;

import Doors.Door;
import Items.Item;
import Location.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NPCIT
{
    private Door d1;
    private Room r1;
    private Room r2;
    private NPC a1;
   private Item list[];


    @Before
    public void setUp()
    {
       // Item list =new Item[];
        d1 = new Door("door");
       // a1= new NPC("test",false,false,list,r1) ;

    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testSwitchOpen1()
    {
        //
        assertEquals(false, d1.isOpen());
        assertEquals("door", d1.getTag());

        //On ouvre:
        d1.open();
        assertEquals(true, d1.isOpen());

        //On ferme:
        d1.close();
        assertEquals(false, d1.isOpen());
    }
}
