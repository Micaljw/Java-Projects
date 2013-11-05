package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.WoodsException;

import two.database.Database;

/**
 * The Class SelectCommand.
 */
public class SelectCommand implements ICommand {
	
	/** The pattern. */
	private final Pattern pattern = Pattern.compile("\\s*select\\s+([\\w|$_]+)(?:\\s+where\\s+(.*))?\\s*;", Pattern.CASE_INSENSITIVE);
	private String tableName;
	private String expression;
	
	/* (non-Javadoc)
	 * @see blech.ICommand#matches(java.lang.String)
	 */
	public boolean matches(String s){	
		Matcher input = pattern.matcher(s);
		boolean doesMatch = input.matches();
		
		if(doesMatch){
			tableName = input.group(1);
			expression = input.group(2);
		}
		
		return doesMatch;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		Database.get().selectTableValues(tableName, expression);
	}
	
}
