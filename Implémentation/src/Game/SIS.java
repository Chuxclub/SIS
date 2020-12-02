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
	}

	public boolean isEndGame()
	{
		return this.ship.getRoom(22).hasActor("player");
	}

	public void endGame()
	{
		System.out.println("Merci d'avoir joué à Silent In Space!");
	}

	public void playTurn()
	{
		this.ship.getPlayer().call();
	}

	public void play()
	{
		while(!this.isEndGame())
		{
			this.playTurn();
		}
	}

}