package ventanas.obstaculos;

import utils.Orientacion;

public class Macetero extends Obstaculo {

	public boolean estasEn(Orientacion o) {
		return o == Orientacion.ABAJO;
	}

}
