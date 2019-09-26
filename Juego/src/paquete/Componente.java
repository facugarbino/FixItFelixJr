package paquete;

import java.awt.Point;

public abstract class Componente {
	protected Point posicion;
	protected Contador timer;
	protected Mapa mapa;
	protected Orientacion orientacion;

	public Point getPosicion() {
		return posicion;
	}

	public void avanzar() {
		if (timer.contar()) {
			timer.resetear();
			comoAvanzo();
		}
	}

	protected abstract void comoAvanzo();

	protected void setOrientacion(Orientacion o) {
		orientacion = o;
	}
}
