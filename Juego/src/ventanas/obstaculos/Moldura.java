package ventanas.obstaculos;

import utils.Orientacion;

public class Moldura extends Obstaculo {

	public boolean estasEn(Orientacion o) {
		return o == Orientacion.ARRIBA;
	}

}
