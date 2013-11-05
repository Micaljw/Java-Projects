package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test projection command.
 */
public class ProjectionCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new ProjectionCommand();
		
		good = new String[] {
				"project emp over student;",
				"PROJECT EMP OVER     PROFESSOR  ;",
				"   project xmandew    oveR  iMP;",
				" project    table1  over    pEAR   ;"
		};
		
		bad = new String[] {
				"projet emp over student;",
				"PROJECT EMP OVR     PROFESSOR  ;",
				"   project xmandew    oveR  iMP",
				" roject    table1  over    pEAR   ;"
		};
	}

}
