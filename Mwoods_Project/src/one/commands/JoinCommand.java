package one.commands;

import java.util.regex.Pattern;

/**
 * The Class JoinCommand.
 */
public class JoinCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*join\\s+([\\w|$_]+)\\s+and\\s+([\\w|$_]+)\\s*;", Pattern.CASE_INSENSITIVE);
//	private String tableName;
//	private String fieldList;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println("This is a correct join command.");
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
