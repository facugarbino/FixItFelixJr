package paquete;

public class HighScore implements Comparable<HighScore> {
	private String nombreJugador;
	private long puntaje;
	
	public HighScore(Jugador j) {
		nombreJugador = j.getNombre();
		puntaje = j.getPuntaje();
	}
	public String getNombre() {
		return nombreJugador;
	}
	public long getPuntaje() {
		return puntaje;
	}
	public int compareTo(HighScore j) {
		return (int) (this.puntaje - j.getPuntaje());
	}
}
