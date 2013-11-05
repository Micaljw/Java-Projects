package one.commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.WoodsException;

import two.database.Database;

/**
 * The Class DeleteCommand.
 */
public class DeleteCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*delete\\s*([\\d\\w|$_]+)(?:\\s+where\\s+(.*))?\\s*;", Pattern.CASE_INSENSITIVE);
	private String tableName;
	private String expression;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		try{
			Database.get().deleteTableInformation(tableName, expression);
		} catch(IOException e){
			throw new WoodsException("Unable to delete the rows.");
		}
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {
		
		Matcher input = pattern.matcher(s);
		boolean doesMatch = input.matches();
		
		if(doesMatch){
			tableName = input.group(1);
			expression = input.group(2);
		}
		
		return doesMatch;
	}

}
