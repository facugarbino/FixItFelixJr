package paquete;

public class Jugador {

	private String nombre;
	private long puntaje;

	/**
	 * 
	 * @param nombre
	 */
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.puntaje = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public long getPuntaje() {
		return puntaje;
	}

	/**
	 * 
	 * @param puntos
	 */
	public void sumarPuntos(long puntos) {
		this.puntaje += puntos;
	}
}
