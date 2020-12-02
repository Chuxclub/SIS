package Doors;

public class Door {

	private boolean isOpen;
	private String tag;

	public Door(String tag)
	{
		this.isOpen = false;
		this.tag = tag;
	}

	public void close()
	{
		this.isOpen = false;
	}

	public void describe()
	{
		if(this.isOpen)
			System.out.println("This door " + this.tag + " is open.");

		else
			System.out.println("This door " + this.tag + " is closed.");
	}

	public String getTag()
	{
		return this.tag;
	}

	public boolean isOpen()
	{
		return this.isOpen;
	}

	public void open()
	{
		this.isOpen = true;
	}
}