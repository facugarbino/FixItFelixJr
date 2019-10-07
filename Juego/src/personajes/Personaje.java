package personajes;

import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import utils.Posicion;

public abstract class Personaje implements Dibujable{
	protected Posicion posicion;
	protected Character caracter;
	
	public Posicion getPosicion() {
		return posicion;
	}
	
	
}
