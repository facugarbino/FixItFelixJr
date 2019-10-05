package ventanas.obstaculos;

import utils.Orientacion;

public class HojaDerecha extends Obstaculo {

	public boolean estasEn(Orientacion o) {
		return o == Orientacion.DERECHA;
	}

}
