package two.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import five.values.AbstractValues;
import five.values.DateValue;

import one.WoodsException;

/**
 * Data type class for date values.
 */
public class DateType extends Datatype{
	
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private String dateFormat;
	private long rowPosition;
	
	/**
	 * Instantiates a new date type.
	 *
	 * @param type the type
	 */
	public DateType(String type, long rowPosition) {
		super(type, "date", rowPosition);
		this.rowPosition = rowPosition;
	}
	
	/* (non-Javadoc)
	 * @see data.Datatype#binaryWrite(java.lang.String, java.io.RandomAccessFile)
	 */
	public void binaryWrite(String value, RandomAccessFile raf) throws WoodsException, IOException{
		try {
			raf.writeLong(sdf.parse(value).getTime());
		} catch (ParseException e) {
			throw new WoodsException("Invalid date. Please enter in the format 'mm/dd/yyyy'.");
		}
	}

	/* (non-Javadoc)
	 * @see data.Datatype#getBinary(java.io.RandomAccessFile)
	 */
	@Override
	public AbstractValues getBinary(RandomAccessFile raf, int row, long rowLength) throws IOException {
		raf.seek(row*rowLength+rowPosition);
		return new DateValue(sdf.format(raf.readLong()));
	}
	
	public AbstractValues getValue(String value){
		return new DateValue(value); 
	}
}
