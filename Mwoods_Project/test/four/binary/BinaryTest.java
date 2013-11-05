package four.binary;


import static org.junit.Assert.*;

import java.io.IOException;
import java.io.RandomAccessFile;

import one.WoodsException;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class BinaryTest.
 */
public class BinaryTest {
	
	/** The ras. */
	RandomAccessFile ras;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		ras = new RandomAccessFile("hello", "rw");
		ras.writeUTF("This is my ras test file.");
		ras.writeBoolean(true);
		ras.writeChar('i');
		ras.writeDouble(12000.50);
		ras.writeInt(50);
	}
	
	/**
	 * Test binary.
	 *
	 * @throws WoodsException the woods exception
	 */
	@Test
	public void testBinary() throws WoodsException{
		try {
			ras.seek(0);
			assertTrue(ras.readUTF().matches("This is my ras test file."));
			assertTrue(ras.readBoolean());
			assertTrue(ras.readChar() == 'i');
			assertTrue(ras.readDouble() == 12000.50);
			assertTrue(ras.readInt() == 50);
			
			ras.seek(0);
			assertFalse(ras.readUTF().matches("This is my ras test fi."));
			assertFalse(!ras.readBoolean());
			assertFalse(ras.readChar() == 'u');
			assertFalse(ras.readDouble() == 1200.50);
			assertFalse(ras.readInt() == 500);
		} catch (IOException e) {
			throw new WoodsException("Unable to read the binary file.");
		}
		
	}

}
