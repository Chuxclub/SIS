package Doors;

import java.io.Serializable;

public class Door implements Serializable {

	private boolean isOpen;
	private final String tag;

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
			System.out.println(this.tag + " is open.");

		else
			System.out.println(this.tag + " is closed.");
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