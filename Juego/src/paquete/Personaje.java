package paquete;

import java.awt.Point;

public abstract class Personaje {
	protected Point posicion;
	protected Timer timer;
	
	public abstract void mover(Orientacion o);
	public Point getPosicion() {
		return posicion;
	}
	
}
