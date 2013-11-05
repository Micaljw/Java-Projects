package two.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import five.values.AbstractValues;
import five.values.BooleanValue;

import one.WoodsException;


/**
 * Data type class for boolean values.
 */
public class BooleanType extends Datatype{

	private long rowPosition;
	
	/**
	 * Instantiates a new boolean type.
	 *
	 * @param type the type
	 */
	public BooleanType(String type, long rowPosition) {
		super(type, "boolean", rowPosition);
		this.rowPosition = rowPosition;
	}
	
	/* (non-Javadoc)
	 * @see data.Datatype#binaryWrite(java.lang.String, java.io.RandomAccessFile)
	 */
	@Override
	public void binaryWrite(String value, RandomAccessFile raf) throws WoodsException, IOException{
		
		if(!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false"))
			throw new WoodsException("Boolean type must be \"true\" or \"false\".");
		
		raf.writeBoolean(Boolean.parseBoolean(value));
	}

	/* (non-Javadoc)
	 * @see data.Datatype#getBinary(java.io.RandomAccessFile)
	 */
	@Override
	public AbstractValues getBinary(RandomAccessFile raf, int row, long rowLength) throws IOException {	
		raf.seek(row*rowLength+rowPosition);
		return new BooleanValue(Boolean.toString(raf.readBoolean()));
	}
	
	public AbstractValues getValue(String value){
		return new BooleanValue(value);
	}
}
