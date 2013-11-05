package two.data;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import two.datatype.BooleanType;
import two.datatype.CharType;
import two.datatype.DateType;
import two.datatype.IntegerType;
import two.datatype.RealType;
import two.datatype.VarcharType;


/**
 * The Class DatatypeTest.
 */
public class DatatypeTest extends AbstractDataTypeTest{


	VarcharType one;
	BooleanType two;
	CharType three;
	DateType four;
	IntegerType five;
	RealType six;
	
	/**
	 * Set test for datatypes.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		one = new VarcharType("name", 0);
		two = new BooleanType("female", 0);
		three = new CharType("initiate", 1, 0);
		four = new DateType("start", 0);
		five = new IntegerType("end", 0);
		six = new RealType("salary", 0);
	}
	
	/**
	 * Test data.
	 */
	@Test
	public void testData(){
		assertTrue(one.toString().matches("varchar name"));
		assertTrue(six.toString().matches("real salary"));
		assertTrue(four.toString().matches("date start"));
		assertTrue(two.toString().matches("boolean female"));
		assertTrue(five.toString().matches("integer end"));
		
		
		assertFalse(one.toString().matches("varchar    name"));
		assertFalse(three.toString().matches("   char initiate"));
		assertFalse(six.toString().matches("real salary  1"));
		assertFalse(four.toString().matches("date starts"));
		assertFalse(two.toString().matches("boolean smale"));
		assertFalse(five.toString().matches("integers end"));
	}

}
