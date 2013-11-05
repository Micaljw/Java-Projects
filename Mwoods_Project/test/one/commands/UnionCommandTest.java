package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test union command.
 */
public class UnionCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new UnionCommand();
		
		good = new String[] {
				"union emp and student;",
				"UNION EMP   AND     PROFESSOR  ;",
				"   union xmandew    anD  iMP;",
				" UnIoN    table1  and    pEAR   ;"
		};
		
		bad = new String[] {
				"unon emp and student;",
				"UNION EMP   AN     PROFESSOR  ;",
				"   union xmandew    anD  iMP",
				" nIoN    table1  and    pEAR   ;"
		};
	}

}
