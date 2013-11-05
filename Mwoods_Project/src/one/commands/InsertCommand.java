package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.WoodsException;

import two.database.Database;

/**
 * The Class InsertCommand.
 */
public class InsertCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*insert\\s*\\((.*?)\\)\\s*into\\s+(.*?)\\s*;", Pattern.CASE_INSENSITIVE);
	private String tableName;
	private String values;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {
		Matcher input = pattern.matcher(s);
		boolean doesMatch = input.matches();	
		
		if(doesMatch){
			tableName = input.group(2);
			values = input.group(1);
		}
		
		return doesMatch;
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException{
		Database.get().insertHandler(values, tableName);
	}

}
