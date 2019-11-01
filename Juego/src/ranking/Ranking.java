package ranking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import juego.Jugador;

/**
 * Almacena el ranking (hasta 5 HighScores) del juego.
 * 
 * @author Garbino y Rodríguez Murphy
 *
 */
public class Ranking {

	private List<HighScore> scores;

	@SuppressWarnings("unchecked")
	public Ranking() {
		scores = new ArrayList<HighScore>();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("ranking.dat"));
			scores = (ArrayList<HighScore>) input.readObject();
			input.close();
		} catch (Exception e) {
			System.out.println("No se encontró el archivo de ranking. Se crea.");
			Jugador jug;
			jug = new Jugador("Tati", 8900);
			agregarHighScore(new HighScore(jug));
			jug = new Jugador("Euge", 12500);
			agregarHighScore(new HighScore(jug));
			jug = new Jugador("Martina", 5000);
			agregarHighScore(new HighScore(jug));
			jug = new Jugador("Uciel", 20000);
			agregarHighScore(new HighScore(jug));
//			jug = new Jugador("Julio", 1000);
//			agregarHighScore(new HighScore(jug));
		}
	}

	public void agregarHighScore(HighScore score) {
		if (scores.size() < 5) {
			scores.add(score);
		} else {
			if (score.getPuntaje() > scores.get(4).getPuntaje()) {
				scores.set(4, score);
			}
		}
		scores.sort(new ComparadorHighScoreDescendente());
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("ranking.dat"));
			output.writeObject(scores);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return una lista ordenada descendentemente con los
	 * 5 mejores puntajes históricos
	 */
	public List<HighScore> getTop5() {
		return this.scores;
		//return this.copia();
	}

//	private List<HighScore> copia() {
//		Iterator<HighScore> iterator = scores.iterator();
//		List<HighScore> lista = new ArrayList<>();
//		while (iterator.hasNext()) {
//			lista.add(iterator.next());
//		}
//		return lista;
//	}

}