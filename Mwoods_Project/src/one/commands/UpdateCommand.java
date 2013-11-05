package one.commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.WoodsException;

import two.database.Database;

/**
 * The Class UpdateCommand.
 */
public class UpdateCommand implements ICommand {
	
	/** The pattern. */
	private final Pattern pattern = Pattern.compile("\\s*update\\s+([\\d\\w|$_]+)\\s+set\\s+(\\w+)\\s*=\\s*(.*?)(?:\\s+where\\s+(.*))?\\s*;", Pattern.CASE_INSENSITIVE);
	private String tableName;
	private String fieldName;
	private String value;
	private String expression;

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		try {
			Database.get().updateHandler(tableName, fieldName, value, expression);
		} catch (IOException e) {
			throw new WoodsException("Unable to update the binary file.");
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
			fieldName = input.group(2);
			value = input.group(3);
			expression = input.group(4);
		}
		
		return doesMatch;
	}

}
