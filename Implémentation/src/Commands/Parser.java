package Commands;

public class Parser {

	Verb verb;

	public Parser(String verb)
	{
		this.verb = this.isValidVerb(verb.toLowerCase());
	}

	public Verb isValidVerb(String verb)
	{
		Verb verbs[] = Verb.values();

		for(Verb v : verbs)
		{
			if(verb.equals(v.getString()))
				return v;
		}

		System.out.println("This verb doesn't exist");
		return null;
	}

	public Verb getVerb()
	{
		return this.verb;
	}
}