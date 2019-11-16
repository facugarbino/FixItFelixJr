package componentes;

import juego.Mapa;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;

/**
 * Clase abstracta que representa a los componentes que están en el aire en el
 * mapa y se mueven.
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public abstract class Componente {
	protected Posicion posicion;
	protected Contador timer;
	protected Mapa mapa;
	protected Orientacion orientacion;

	public Posicion getPosicion() {
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
