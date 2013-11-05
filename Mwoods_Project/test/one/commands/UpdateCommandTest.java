package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test update command.
 */
public class UpdateCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new UpdateCommand();
		
		good = new String[] {
				"update emp4 set cashier = 10 where x != 4;",
				"UPDATE EMP SET CASHIER = 4 WHERE X != '04/12/1988 ';",
				"   UPDATE xman325dew   set Dave = 0 ;",
				" Update table1 set dave   =    0 where p <    ' 08/08/2008';"
		};
		
		bad = new String[] {
				"update emp set cashier = hired where x != 4",
				"UPDAT EMP SET CASHIER WHERE X != '04/12/1988';",
				"   UPDATE xmandew   se Dave = 0 ;",
				" Delete table1 set dave   =     where p <    '08/08/2008';"
		};
	}

}
