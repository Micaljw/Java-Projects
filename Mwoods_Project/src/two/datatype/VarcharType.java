package two.datatype;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import five.values.AbstractValues;
import five.values.VarcharValue;

import one.WoodsException;

/**
 * Data type class for varchar values.
 */
public class VarcharType extends Datatype{
	
	private static RandomAccessFile stringRaf;
	static {
		try {
			stringRaf = new RandomAccessFile("varchar", "rw");
		} catch (FileNotFoundException e) {
			System.out.println("Unable to create varchar raf file.");
		}
	}
	private Long store;
	private long value;
	private long rowPosition;
	
	/**
	 * Instantiates a new varchar type.
	 *
	 * @param type the type
	 * @throws WoodsException the woods exception
	 */
	public VarcharType(String type, long rowPosition) {
		super(type, "varchar", rowPosition);
		this.rowPosition = rowPosition;
	}

	/* (non-Javadoc)
	 * @see data.Datatype#binaryWrite(java.lang.String, java.io.RandomAccessFile)
	 */
	@Override
	public void binaryWrite(String input, RandomAccessFile raf) throws IOException {	
		store = stringRaf.length();
		stringRaf.seek(store);
		stringRaf.writeUTF(input);
		raf.writeLong(store);
	}

	/* (non-Javadoc)
	 * @see data.Datatype#getBinary(java.io.RandomAccessFile)
	 */
	@Override
	public AbstractValues getBinary(RandomAccessFile raf, int row, long rowLength) throws IOException {
		raf.seek(row*rowLength+rowPosition);
		value = raf.readLong();
		stringRaf.seek(value);
		return new VarcharValue(stringRaf.readUTF());
	}
	
	public AbstractValues getValue(String value){
		return new VarcharValue(value);
	}
}
