package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test delete command.
 */
public class DeleteCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new DeleteCommand();
		
		good = new String[] {
				"delete emp4 where x != 4;",
				"DELETE EMP WHERE X != ' 04/12/1988';",
				"   Delete xmand345ew    wherE  i >     1  ;",
				" Delete table1 where p <    '08/08/2008 ';"
		};
		
		bad = new String[] {
				"delet emp where x != 4;",
				"DELETE EMP WHEREX != '04/12/1988';",
				"   Delete xmandew    wherE  i >     1  ",
				" Delete table1 where p     '08/08/2008'"
		};
	}

}
