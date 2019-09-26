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
		// Devolvemos > 0 si el receptor es menor, para ordenar
		// el arreglo descendentemente.
		return (int) (j.getPuntaje() - this.puntaje);
	}
}
