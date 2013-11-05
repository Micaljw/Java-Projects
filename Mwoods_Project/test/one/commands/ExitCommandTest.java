package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test exit command.
 */
public class ExitCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new ExitCommand();
		
		good = new String[] {
				"Exit;",
				"EXIT     ;",
				"      eXiT;",
				"    exit           ;"
		};
		
		bad = new String[] {
				"Ext;",
				"EXIT     ",
				"      XiT;",
				"    exit       23;    "
		};
	}

}
