package five;

import one.WoodsException;

public class NoMatch implements IMatcher{

	@Override
	public boolean isMatch(int row) {
		return true;
	}
}
