package ranking;

import java.util.Comparator;

public class ComparadorHighScoreDescendente implements Comparator<HighScore> {

	@Override
	public int compare(HighScore o1, HighScore o2) {
		return o2.compareTo(o1);
	}

}
