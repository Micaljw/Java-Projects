package two.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import five.values.AbstractValues;
import five.values.CharValue;

import one.WoodsException;

/**
 * Data type class for char values.
 */
public class CharType extends Datatype{
	
	private int parseChar;
	private long rowPosition;
	/**
	 * Instantiates a new char type.
	 *
	 * @param name the name
	 * @param parseChar the parse char
	 */
	public CharType(String name, int parseChar, long rowPosition) {
		super(name, "char", rowPosition);
		this.parseChar = parseChar;
		this.rowPosition = rowPosition;
	}
	
	/* (non-Javadoc)
	 * @see data.Datatype#toString()
	 */
	public String toString(){
		return "char" + "(" + parseChar + ") " + name;
	}
	
	/* (non-Javadoc)
	 * @see data.Datatype#toXMLString()
	 */
	public String toXMLString(){
		return super.toXMLString() + "<size>" + parseChar + "</size>";
	}
	
	/* (non-Javadoc)
	 * @see data.Datatype#binaryWrite(java.lang.String, java.io.RandomAccessFile)
	 */
	@Override
	public void binaryWrite(String value, RandomAccessFile raf) throws WoodsException, IOException{
		
		if(value.length() > parseChar)
			throw new WoodsException("The number of characters input is too large.");
		else if(value.length() < parseChar)
			throw new WoodsException("The number of characters input is too small.");
		
		for(int i = 0; i < value.length(); i++){
			raf.writeChar(value.charAt(i));
		}
	}

	/* (non-Javadoc)
	 * @see data.Datatype#getBinary(java.io.RandomAccessFile)
	 */
	@Override
	public AbstractValues getBinary(RandomAccessFile raf, int row, long rowLength) throws IOException{
		String character = "";
		
		raf.seek(row*rowLength+rowPosition);
		
		for(int i = 0; i < parseChar; i++)
			character += Character.toString(raf.readChar());
		
		return new CharValue(character);
	}
	
	public AbstractValues getValue(String value){
		return new CharValue(value);
	}
}
