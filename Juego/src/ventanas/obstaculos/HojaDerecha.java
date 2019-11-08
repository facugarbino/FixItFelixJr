package ventanas.obstaculos;

import utils.Orientacion;

public class HojaDerecha extends Obstaculo {

	public HojaDerecha() {
		posicion = posicionHojaDerecha;
	}
	public boolean estasEn(Orientacion o) {
		return o == Orientacion.DERECHA;
	}

}
