package paquete;

import java.awt.Point;

public class Edificio {
	private Point posicion;
	private List<Seccion> secciones;
	
	public Edificio(Point posicion, List<Seccion> secciones){
		this.posicion = posicion;
		this.secciones = secciones;
		
	}
}
