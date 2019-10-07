package ranking;

import java.io.Serializable;

import juego.Jugador;

@SuppressWarnings("serial")
public class HighScore implements Comparable<HighScore>, Serializable {
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
		//return (int) (j.getPuntaje() - this.puntaje);
		return (int) (this.puntaje - j.getPuntaje());
	}
	
	public String toString() {
		return (nombreJugador+": "+puntaje);
	}
}
