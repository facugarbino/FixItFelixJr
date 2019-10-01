package paquete;

public class Jugador {

	private String nombre;
	private long puntaje;

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

	public void sumarPuntos(long puntos) {
		this.puntaje += puntos;
	}
}
