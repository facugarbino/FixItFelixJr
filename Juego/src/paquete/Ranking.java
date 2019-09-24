package paquete;

import java.util.Arrays;

public class Ranking {
	
	private HighScore[] scores;
	private int cantScores;
	
	public Ranking() {
		 scores = new HighScore[4];
		 cantScores=0;
	}
	
	public void agregarHighScore (HighScore score) {
		if (cantScores<5) {
			scores[cantScores++] = score;
		} else {
			scores[5] = score;
		}
		Arrays.sort(scores);
	}
	
	public HighScore[] getTop5(){
		return scores;
	}
	

}


