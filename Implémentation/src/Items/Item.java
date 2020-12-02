package Items;

public abstract class Item implements Usable {

	private String tag;
	private String description;

	public Item(String tag, String description)
	{
		this.tag = tag;
		this.description = description;
	}

	public abstract void describe();

	public String getTag()
	{
		return this.tag;
	}

	public String getDescription()
	{
		return this.description;
	}
}