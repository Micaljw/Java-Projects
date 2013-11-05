package one.commands;

import java.util.regex.Pattern;

/**
 * The Class ProjectionCommand.
 */
public class ProjectionCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*project\\s+(.*?)\\s+over\\s+(\\w+)\\s*;", Pattern.CASE_INSENSITIVE);
//	private String tableName;
//	private String fieldList;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println("This is a correct projection command.");
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {
		
//		if(doesMatch){
//			tableName = input.group(1);
//			fieldList = input.group(2);
//		}
		
		return pattern.matcher(s).matches();
	}

}
