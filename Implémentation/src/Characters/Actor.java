package Characters;

import java.util.*;
import Items.*;
import Location.*;

public abstract class Actor implements Attackable {

	Collection<Item> inventory;
	Room room;
	private int hp;
	private int DEFAULT_ATTACK;
	private int DEFAULT_HP;

}