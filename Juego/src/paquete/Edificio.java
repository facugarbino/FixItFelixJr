package paquete;

import java.util.List;

public class Edificio {
	private Posicion posicion;
	private List<Seccion> secciones;
	private Seccion seccionActual;

	public Edificio(Posicion posicion, List<Seccion> secciones) {
		this.posicion = posicion;
		this.secciones = secciones;
		this.seccionActual = secciones.get(0);
	}

	public Seccion getSeccion(int i) {
		return secciones.get(i + 1);
	}

	public Seccion getSeccionActual() {
		return seccionActual;
	}

	public void avanzarSeccion() {
		this.seccionActual = secciones.get(secciones.indexOf(seccionActual) + 1);
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void reemplazarSeccion(Seccion nueva, Seccion vieja) {
		secciones.set(secciones.indexOf(vieja), nueva);
	}
}
