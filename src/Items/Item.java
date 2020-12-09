package Items;

import Commands.Lookable;

import java.io.Serializable;

public abstract class Item implements Usable, UsableBy, UsableOn, Serializable, Lookable {

	private final String tag;
	private final String description;
	private final boolean isTakable;
	private final boolean isGivable;

	public Item(String tag, String description)
	{
		this.tag = tag;
		this.description = description;
		this.isTakable = false;
		this.isGivable = false;
	}

	public Item(String tag, String description, boolean isTakable, boolean isGivable)
	{
		this.tag = tag;
		this.description = description;
		this.isTakable = isTakable;
		this.isGivable = isGivable;
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

	public boolean isTakable() {
		return this.isTakable;
	}

	public boolean isGivable() {
		return this.isGivable;
	}
}