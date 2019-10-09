package ventanas.obstaculos;

import utils.Orientacion;

/**
 * Clase abstracta que representa a un obstáculo de cualquier tipo.
 * 
 * @author Garbino y Rodríguez Murphy
 *
 */
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
