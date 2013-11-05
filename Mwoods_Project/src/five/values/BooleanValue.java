package five.values;

import java.io.IOException;
import java.io.RandomAccessFile;

import one.WoodsException;

public class BooleanValue extends AbstractValues<BooleanValue>{

	private final String userString;
	
	public BooleanValue(String userString) {
		this.userString = userString;
	}

	@Override
	public int compareTo(BooleanValue o) {
		return userString.compareTo(o.userString);
	}

	@Override
	public String toString() {
		return userString;
	}
}
