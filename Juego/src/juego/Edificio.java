package juego;

import java.util.List;

import utils.Posicion;

public class Edificio {
	public static final int ANCHO = 100;
	public static final int ALTO = 3*ANCHO;
	private Posicion posicion;
	private List<Seccion> secciones;
	private Seccion seccionActual;

	public Edificio(Posicion posicion, List<Seccion> secciones) {
		this.posicion = posicion;
		this.secciones = secciones;
		this.seccionActual = secciones.get(0);
	}

	public Seccion getSeccion(int i) {
		return secciones.get(i - 1);
	}

	public Seccion getSeccionActual() {
		return seccionActual;
	}

	public Seccion avanzarSeccion() {
		this.seccionActual = secciones.get(secciones.indexOf(seccionActual) + 1);
		return seccionActual;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void reemplazarSeccion(Seccion vieja) {
		Seccion nueva;
		if (vieja instanceof PrimeraSeccion) {
			nueva = new PrimeraSeccion(vieja);
			secciones.set(0, nueva);
		} else {
			nueva = new Seccion(vieja);
			secciones.set(vieja.getNroSeccion()-1, nueva);
		}
		this.seccionActual = nueva;
	}
}
