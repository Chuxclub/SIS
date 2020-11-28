package Commands;

import Characters.*;
import java.util.*;

public class Command {

	Player caller;
	Parser parser;
	Collection<Executable> execs;
	private string verb;
	private List<string> args;

	/**
	 * 
	 * @param verb
	 */
	public void parse(int verb) {
		// TODO - implement Command.parse
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param commandWords
	 * @param args
	 */
	public void exec(int commandWords, int args) {
		// TODO - implement Command.exec
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param string
	 * @param args
	 */
	public Command(int string, int args) {
		// TODO - implement Command.Command
		throw new UnsupportedOperationException();
	}

}