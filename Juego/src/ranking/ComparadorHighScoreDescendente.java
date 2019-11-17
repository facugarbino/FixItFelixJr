package ranking;

import java.util.Comparator;

/**
 * Comparador para ordenar descendentemente la colección
 * de tops 5 jugadores con más puntaje.
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class ComparadorHighScoreDescendente implements Comparator<HighScore> {

	@Override
	public int compare(HighScore o1, HighScore o2) {
		return o2.compareTo(o1);
	}

}
