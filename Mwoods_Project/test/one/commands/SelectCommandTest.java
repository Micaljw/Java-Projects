package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test select command.
 */
public class SelectCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new SelectCommand();
		
		good = new String[] {
				"select emp where x != 4;",
				"SELECT EMP WHERE X != '04/12/1988';",
				"   Select xmandew    wherE  i >     1  ;",
				" Select table1 where p <    '08/08/2008';"
		};
		
		bad = new String[] {
				"select emp where x != 4",
				"SELET EMP WHERE X != '04/12/1988';",
				"   Select xmandew    wheE  x >     1  ;",
				" Select table1 where     '08/08/2008'"
		};
	}

}
