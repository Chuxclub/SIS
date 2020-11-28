package Game;
import Location.Ship;
import Characters.Player;

public class GameMaster
{
	private Ship ship;
	private Player player;
	private boolean isGameOver;

	public GameMaster()
	{
		this.initGame();
	}

	public void initGame()
	{
		this.ship = new Ship(this.player);
		this.player = new Player();
		this.isGameOver = false;
	}

	public void playTurn()
	{
		this.player.call();
	}

	public void isEndGame()
	{
		if(this.player.getRoom() == this.ship.searchRoom(22))
			this.isGameOver = true;
	}

	public void endGame()
	{
		System.out.println("GG Hello World");
	}

	public void play()
	{
		while(!isGameOver)
		{
			this.playTurn();
			this.isEndGame();
		}

		this.endGame();
	}

}