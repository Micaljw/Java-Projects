package one.commands;

import java.util.regex.Pattern;

/**
 * The Class DefineIndexCommand.
 */
public class DefineIndexCommand implements ICommand {


	private final Pattern pattern = Pattern.compile("\\s*define\\s+index\\s+on\\s+([\\d\\w_|$]+)\\s*\\(\\s*(\\w+)\\s*\\)\\s*;", Pattern.CASE_INSENSITIVE);
//	private String tableName;
//	private String fieldName;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println("This is a correct define index command.");
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {;	
		
//		if(doesMatch){
//			tableName = input.group(1);
//			fieldName = input.group(2);
//		}
		
		return pattern.matcher(s).matches();
	}

}
