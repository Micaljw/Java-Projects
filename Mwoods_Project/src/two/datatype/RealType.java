package two.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import five.values.AbstractValues;
import five.values.RealValue;

/**
 * Data type class for real values.
 */
public class RealType extends Datatype{

	private long rowPosition;
	
	/**
	 * Instantiates a new real type.
	 *
	 * @param type the type
	 */
	public RealType(String type, long rowPosition) {
		super(type, "real", rowPosition);
		this.rowPosition = rowPosition;
	}

	/* (non-Javadoc)
	 * @see data.Datatype#binaryWrite(java.lang.String, java.io.RandomAccessFile)
	 */
	@Override
	public void binaryWrite(String input, RandomAccessFile raf) throws NumberFormatException, IOException {
		raf.writeDouble(Double.parseDouble(input));
	}

	/* (non-Javadoc)
	 * @see data.Datatype#getBinary(java.io.RandomAccessFile)
	 */
	@Override
	public AbstractValues getBinary(RandomAccessFile raf, int row, long rowLength) throws IOException {
		raf.seek(row*rowLength+rowPosition);
		return new RealValue(Double.toString(raf.readDouble()));
	}
	
	public AbstractValues getValue(String value){
		return new RealValue(value);
	}

}
