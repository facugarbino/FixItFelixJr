package paquete;

import java.awt.Point;
import java.util.List;

public class Edificio {
	private Point posicion;
	private List<Seccion> secciones;
	private Seccion seccionActual;
	
	public Edificio(Point posicion, List<Seccion> secciones){
		this.posicion = posicion;
		this.secciones = secciones;
		this.seccionActual= secciones.get(0);
	}
	
	public Seccion getSeccion() {
		return seccionActual;
	}
	public void avanzarSeccion() {
		this.seccionActual= secciones.get(secciones.indexOf(seccionActual)+1);
	}
}
