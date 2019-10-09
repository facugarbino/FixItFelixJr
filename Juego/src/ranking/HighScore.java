package ranking;

import java.io.Serializable;

import juego.Jugador;
/**
 * 
 * Representa a un HighScore (con el nombre del jugador y su puntaje)
 * @author Garbino y Rodriguez Murphy
 *
 */
//Implementa Serializable para poder ser escrito/leído de un archivo binario.
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
		return (int) (this.puntaje - j.getPuntaje());
	}
	
	public String toString() {
		return (nombreJugador+": "+puntaje);
	}
}
