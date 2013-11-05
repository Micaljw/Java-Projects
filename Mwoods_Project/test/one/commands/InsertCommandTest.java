package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test insert command.
 */
public class InsertCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new InsertCommand();
		
		good = new String[] {
				"insert(33, 'hello', '03/22/2012') into students;",
				"insert ( 33,  'hello' ,    '03/22/2012') into   students ;",
				"insert ('beef', 1000     ,    '05/23/2012'  ) into restaraunt   ;",
				"  insert(12345, 'cs210', '12/12/2050')   into    wvu;"
		};
		
		bad = new String[] {
				"inset(33, 'hello', '03/22/2012') into students;",
				"insert  33,  'hello' ,    '03/22/2012') into   students ;",
				"insert ('beef', 1000     ,    '05/23/2012'  ) ito restaraunt   ;",
				"  insert(12345, 'cs210', '12/12/2050'   into    wvu;"
				
		};
	}

}
