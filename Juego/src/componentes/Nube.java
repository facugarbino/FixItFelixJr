package componentes;

import utils.Contador;
import utils.Orientacion;
import utils.Posicion;

public class Nube extends Componente {

	public Nube(Posicion p, int frecuencia) {
		posicion = p;
		timer = new Contador(frecuencia);
		orientacion = Orientacion.DERECHA;
	}
	/**
	 * Hace la lógica para que la nube se mueva de extremo a extremo
	 */
	protected void comoAvanzo() {
			if (orientacion == Orientacion.IZQUIERDA) {
				if (posicion.getX() > 0) {
					posicion.moverX(-1);
				} else {
					setOrientacion(Orientacion.DERECHA);
					comoAvanzo();
				}
			} else {
				if (posicion.getX() < (200)) {
					posicion.moverX(1);
				} else {
					setOrientacion(Orientacion.IZQUIERDA);
					comoAvanzo();
				}
			}
	}

}
