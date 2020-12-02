package Items;


import Characters.Actor;

public class HealthStation extends Item implements Usable
{
	private static final int DEFAULT_HEALINGPOINTS = 100;
	public HealthStation(String tag, String description)
	{
		super(tag, description);
	}

	@Override
	public void describe()
	{
		System.out.println(this.getDescription());
	}

	public int getHealingPoints()
	{
		return DEFAULT_HEALINGPOINTS;
	}


	public void isUsed(Actor player)
	{
		player.setHP(player.getDEFAULT_HP_MAX());
	}

	@Override
	public void isUsed()
	{
		System.out.println("I need you to tell me who needs to be healed.");
	}

	@Override
	public void isUsedBy(UsableOn u)
	{ }
}