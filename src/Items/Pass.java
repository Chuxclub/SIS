package Items;


import java.io.Serializable;

public class Pass extends Item implements UsableOn, Serializable {

	PassType passType;

	public Pass(String tag, String description, PassType p) {
		super(tag, description, true, false);
		this.passType = p;
	}

    public PassType getPassType() {
		return this.passType;
	}

	@Override
	public void isUsed(UsableBy u) {
		System.out.println("Your pass must be used on something !");
	}
}

