package five.values;

import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class AbstractValues<T extends AbstractValues> implements Comparable<T>{
	public abstract String toString();
}
