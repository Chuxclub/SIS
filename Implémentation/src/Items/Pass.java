package Items;

public class Pass extends Item implements UsableOn {

	PassType passType;

	public Pass(String tag, String description, PassType p)
	{
		super(tag, description);
		this.passType = p;
	}

	public PassType getPassType()
	{
		return this.passType;
	}

	@Override
	public void describe() {

	}

	@Override
	public void isUsed() {

	}
}