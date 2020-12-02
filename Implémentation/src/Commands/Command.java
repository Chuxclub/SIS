package Commands;

import Characters.*;
import Doors.Door;
import Items.Item;

import java.util.List;

public class Command {

	Player caller;
	Parser parser;
	Converter converter;
	private String verb;
	private List<String> args;
	private Verb v;

	public Command(Player player, String verb, List<String> args)
	{
		this.caller = player;
		this.verb = verb;
		this.args = args;

		this.parser = new Parser(this.verb);
		this.v = this.parser.getVerb();

		this.converter = new Converter(this.caller);
	}

	public void exec()
	{
		switch(this.v)
		{
			case GO:
				Door d = this.converter.convertDoor(this.args.get(0));
				this.caller.go(d);
				break;

			case HELP:
				this.caller.help();
				break;

			case LOOK:
				if(args.size() == 0)
					this.caller.look();

				else
				{
					Item item = this.converter.convertItem(this.args.get(0));
					this.caller.look(item);
				}
				break;

			case TAKE:
				break;


			case QUIT:
				caller.quit();
				break;

			case USE:
				break;

			case INVENTORY:
				break;

			case BACK:
				this.caller.back();
				break;
		}
	}

}