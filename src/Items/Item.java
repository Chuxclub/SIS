package Items;

import Commands.Lookable;

import java.io.Serializable;

public abstract class Item implements Usable, UsableBy, UsableOn, Serializable, Lookable {

	private final String tag;
	private final String description;

	public Item(String tag, String description)
	{
		this.tag = tag;
		this.description = description;
	}

	@Override
	public void describe()
	{
		System.out.print(this.getDescription());
	}

	public String getTag()
	{
		return this.tag;
	}

	public String getDescription()
	{
		return this.description;
	}
}