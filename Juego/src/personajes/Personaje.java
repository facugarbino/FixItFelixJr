package personajes;

import paquete.Posicion;
import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

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
