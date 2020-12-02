package Characters;

import Location.Room;

import java.util.Scanner;

public class Player extends Actor {

	private static String NAME = "player";
	private Room previous_room;

	public Player(Room r)
	{
		super(NAME, r);
		this.previous_room = r;
	}

	public void back()
	{
		this.changeRoom(this.previous_room);
	}

	public void call()
	{
		System.out.print("Command :> ");
		Scanner sc = new Scanner(System.in);

		String verb = sc.next();

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