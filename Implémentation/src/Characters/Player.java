package Characters;

import Location.Room;

public class Player extends Actor {

	private static String NAME = "player";

	public Player(Room r)
	{
		super(NAME, r);
	}

	public void back()
	{
	}

	public void call()
	{
	}

	public void go(int Door)
	{
	}

	public void help()
	{
	}

	public void look()
	{
	}

	public void look(int Item)
	{
	}

	public void take(int Item)
	{
	}

	public void quit()
	{
	}

	public void use(int Item)
	{
	}

	public void use(int UsableOn, int UsableBy)
	{
	}

	@Override
	public void isAttacked() {

	}

	@Override
	public void attack(Attackable a) {

	}
}