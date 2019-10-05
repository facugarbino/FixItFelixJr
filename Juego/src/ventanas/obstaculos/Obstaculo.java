package ventanas.obstaculos;

import utils.Orientacion;

public abstract class Obstaculo {

	/**
	 * 
	 * @param orientacion
	 * @return <b>true</b> si el obstáculo se encuentra
	 * posicionado en la ventana, en el sentido indicado
	 * 
	 * @see Por ejemplo: para el macetero, devuelve <b>true</b>
	 * si el sentido es <k>abajo</k>
	 */
	public abstract boolean estasEn(Orientacion orientacion);

}
