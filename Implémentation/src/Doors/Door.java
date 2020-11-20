package Doors;

public class Door {

	private boolean isOpen;

	public Door()
	{
		this.isOpen = false;
	}

	public void open()
	{
		this.isOpen = true;
	}

	public void close()
	{
		this.isOpen = false;
	}

}