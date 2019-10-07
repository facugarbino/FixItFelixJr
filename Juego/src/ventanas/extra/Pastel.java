package ventanas.extra;

import utils.Posicion;

import java.awt.Color;

import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;

public class Pastel implements Dibujable {
	
	private Posicion posicion;

	public Pastel(Posicion posicion) {
		this.posicion = posicion;
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(posicion.getX(),posicion.getY(),'ó', Color.BLACK);
	}
	
	

}
