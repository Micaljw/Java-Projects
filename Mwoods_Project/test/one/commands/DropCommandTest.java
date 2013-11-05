package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test drop command.
 */
public class DropCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new DropCommand();
		
		good = new String[] {
				"drop table fired;",
				"DROP TABLE FIRED2   ;",
				"    droP  TabLe  suicide;",
				"drop table grieving456Family  ;"
		};
		
		bad = new String[] {
				"drp table fired;",
				"DROP  tabl FIRED  ;",
				"    droP   mk suicide...;",
				" drop  tablegrievingFamily:(;"
		};
	}
	
}
