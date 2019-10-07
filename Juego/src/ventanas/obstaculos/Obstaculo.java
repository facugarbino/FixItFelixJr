package ventanas.obstaculos;

import utils.Orientacion;

public abstract class Obstaculo {

	/**
	 * 
	 * @return <b>true</b> si el obstáculo se encuentra
	 * posicionado en la ventana, en el sentido indicado
	 * 
	 * Por ejemplo: para el macetero, devuelve <b>true</b>
	 * si el sentido es <i>abajo</i>
	 */
	public abstract boolean estasEn(Orientacion orientacion);

}
