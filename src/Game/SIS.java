package Game;


import Location.*;

public class SIS {

	Ship ship;
	private static final int NB_PLAYERS = 1;

	public SIS()
	{
		this.initGame();
		this.play();
		this.endGame();
	}

	public void initGame()
	{
		this.ship = new Ship();
		this.ship.getNPC("Kilen").talk();
		this.ship.getNPC("Kilen").give("passT", this.ship.getPlayer());
	}

	public boolean isEndGame()
	{
		return this.ship.getRoom(22).hasActor("me");
	}

	public void endGame()
	{
		System.out.println("\nThanks for playing Silent In Space!");
	}

	public void playTurn()
	{
		this.ship.getPlayer().call();
	}

	public void play()
	{
		while(true || !this.isEndGame())
		{
			this.playTurn();
		}
	}

}