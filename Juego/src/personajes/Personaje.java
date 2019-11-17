package personajes;

import utils.Posicion;
/**
 * Clase abstracta que ayuda a modelar a los personajes
 * que poseen una posición en el juego (Ralph y Felix)
 * 
 * @author Garbino y Rodríguez Murphy
 *
 */
public abstract class Personaje{
	protected Posicion posicion;
	
	public Posicion getPosicion() {
		return posicion;
	}
	
	
}
