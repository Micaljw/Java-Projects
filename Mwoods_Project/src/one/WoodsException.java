package one;

/**
 * Throw-able class to handle any exception.
 */
@SuppressWarnings("serial")
public class WoodsException extends Exception {

	/**
	 * Instantiates a new woods exception.
	 */
	public WoodsException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new woods exception.
	 *
	 * @param arg0 the arg0
	 */
	public WoodsException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new woods exception.
	 *
	 * @param arg0 the arg0
	 */
	public WoodsException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new woods exception.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public WoodsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
