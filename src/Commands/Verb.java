package Commands;

public enum Verb {
	GO("go"), HELP("help"), LOOK("look"),
	TAKE("take"), QUIT("quit"), USE("use"),
	INVENTORY("inventory"), BACK("back"), INFO("info"),
	DROP("drop"), ATTACK("attack"), SAVE("save"), LOAD("load");
	private String strValue;

	Verb(String s)
	{
		this.strValue = s;
	}

	public String getString()
	{
		return this.strValue;
	}
}