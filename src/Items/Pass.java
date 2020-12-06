package Items;


import java.io.Serializable;

public class Pass extends TakableItem implements UsableOn, Serializable {

	PassType passType;

	public Pass(String tag, String description, PassType p) {
		super(tag, description);
		this.passType = p;
	}

    public PassType getPassType() {
		return this.passType;
	}

	@Override
	public void isUsed(UsableBy u) {
		System.out.println("Your pass must be used on something !");
	}
	@Override
	public void isUsedBy(UsableOn u)
	{ }

	@Override
	public TakableItem getCopy() {
		return  new Pass(this.getTag(), this.getDescription(), this.getPassType());
	}
}

