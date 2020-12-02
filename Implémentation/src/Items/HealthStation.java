package Items;

public class HealthStation extends Item implements Usable
{
	public HealthStation(String tag, String description)
	{
		super(tag, description);
	}

	@Override
	public void describe()
	{
		System.out.println(this.getDescription());
	}

	public void isUsed()
	{
	}
}