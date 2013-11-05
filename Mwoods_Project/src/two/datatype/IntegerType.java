package two.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import five.values.AbstractValues;
import five.values.IntValue;

/**
 * Data type class for integer values.
 */
public class IntegerType extends Datatype{
	
	private long rowPosition;
	/**
	 * Instantiates a new integer type.
	 *
	 * @param type the type
	 */
	public IntegerType(String type, long rowPosition) {
		super(type, "integer", rowPosition);
		this.rowPosition = rowPosition;
	}
	
	/* (non-Javadoc)
	 * @see data.Datatype#binaryWrite(java.lang.String, java.io.RandomAccessFile)
	 */
	@Override
	public void binaryWrite(String value, RandomAccessFile raf) throws IOException{
		raf.writeInt(Integer.parseInt(value));	
	}

	/* (non-Javadoc)
	 * @see data.Datatype#getBinary(java.io.RandomAccessFile)
	 */
	@Override
	public AbstractValues getBinary(RandomAccessFile raf, int row, long rowLength) throws IOException {
		raf.seek(row*rowLength+rowPosition);
		return new IntValue(Integer.toString(raf.readInt()));
	}
	
	public AbstractValues getValue(String value){
		return new IntValue(value);
	}
}
