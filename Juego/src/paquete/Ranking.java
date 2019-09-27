package paquete;

import java.util.Arrays;

public class Ranking {

	private HighScore[] scores;
	private int cantScores;

	public Ranking() {
		scores = new HighScore[5];
		cantScores = 0;
		for (int i=0;i<5;i++)
			scores[i] = new HighScore(new Jugador(""));
	}

	public void agregarHighScore(HighScore score) {
		if (cantScores < 5) {
			scores[cantScores++] = score;
		} else {
			if (score.getPuntaje() > scores[4].getPuntaje()) {
				scores[4] = score;
			}
		}
		Arrays.sort(scores);
	}

	public HighScore[] getTop5() {
		return scores;
	}

}
