package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test intersection command.
 */
public class IntersectionCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new IntersectionCommand();
		
		good = new String[] {
				"intersect emp and student;",
				"INTERSECT EMP   AND     PROFESSOR  ;",
				"   intersect xmandew    anD  iMP;",
				" INterSecT    table1  and    pEAR   ;"
		};
		
		bad = new String[] {
				"intersct emp and student;",
				"INTERSECT EMP   AN     PROFESSOR  ;",
				"   intersect xmandew    anD  iMP",
				" NterSecT    table1  and    pEAR   ;"
		};
	}

}
