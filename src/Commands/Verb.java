package Commands;

public enum Verb {
	ATTACK("attack"), BACK("back"), DROP("drop"), GO("go"), HELP("help"),
	INFO("info"), INVENTORY("inventory"), LOAD("load"),  LOOK("look"), QUIT("quit"),
	SAVE("save"), TAKE("take"), TALK("talk"), USE("use");
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