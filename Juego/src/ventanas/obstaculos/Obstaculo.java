package ventanas.obstaculos;

import utils.Orientacion;
import utils.Posicion;

/**
 * Clase abstracta que representa a un obstáculo de cualquier tipo.
 * 
 * @author Garbino y Rodríguez Murphy
 *
 */
public abstract class Obstaculo {

	protected Posicion posicion;
	protected Posicion posicionMacetero = new Posicion(0,-5);
	protected Posicion posicionMoldura = new Posicion(0,35);
	protected Posicion posicionHojaIzquierda = new Posicion(-2,0);
	protected Posicion posicionHojaDerecha = new Posicion(26,0);
	/**
	 * 
	 * @return <b>true</b> si el obstáculo se encuentra
	 * posicionado en la ventana, en el sentido indicado
	 * 
	 * Por ejemplo: para el macetero, devuelve <b>true</b>
	 * si el sentido es <i>abajo</i>
	 */
	public abstract boolean estasEn(Orientacion orientacion);
	
	public Posicion getPosicion() {
		return posicion;
	}

}

