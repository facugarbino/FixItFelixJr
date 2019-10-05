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
	
	@Override
	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(posicion.getX(), posicion.getY(), caracter);
	}
	
}
