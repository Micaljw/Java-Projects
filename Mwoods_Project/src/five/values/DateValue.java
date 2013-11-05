package five.values;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import one.WoodsException;

public class DateValue extends AbstractValues<DateValue>{
	
	private String userStr;
	
	public DateValue(String value){
		this.userStr = value;
	}

	@Override
	public int compareTo(DateValue o) {
		return userStr.compareTo(o.userStr);
	}

	@Override
	public String toString() {
		return userStr;
	}
}
