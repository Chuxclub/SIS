package Items;

public abstract class Item implements Usable, UsableBy, UsableOn {

	private final String tag;
	private final String description;

	public Item(String tag, String description)
	{
		this.tag = tag;
		this.description = description;
	}

	public void describe()
	{
		System.out.print(getDescription());
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