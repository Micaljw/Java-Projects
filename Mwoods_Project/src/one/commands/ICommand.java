package one.commands;

import one.WoodsException;

/**
 * Handle the behavior of all commands.
 */
public interface ICommand {

	/**
	 * Validate the user's command.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	boolean matches(String s);
	
	/**
	 * Execute the purpose of corresponding command.
	 *
	 * @throws WoodsException the woods exception
	 */
	void execute() throws WoodsException;

}
