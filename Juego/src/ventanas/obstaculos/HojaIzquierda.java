package ventanas.obstaculos;

import utils.Orientacion;

public class HojaIzquierda extends Obstaculo {

	public boolean estasEn(Orientacion o) {
		return o == Orientacion.IZQUIERDA;
	}

}
