package componentes;

import java.awt.Color;

import juego.Juego;
import juego.Mapa;
import utils.Contador;
import utils.Posicion;

public class Ladrillo extends Componente {

	private static final int ANCHO = 3;
	private Mapa mapa;

	public Ladrillo(Posicion p, int velocidad, Mapa m) {
		posicion = p;
		timer = new Contador(velocidad);
		mapa = m;
		caracter = 'O';
		color = new Color(95,2,31);
	}

	/**
	 * Tiene la lógica para que un ladrillo avance
	 * (caiga verticalmente) a determinada velocidad
	 */
	protected void comoAvanzo() {
		posicion.moverY(-1);
		//System.out.println("Un ladrillo avanza a la posicion " + posicion);
		if (mapa.estaFelix(posicion, ANCHO)) {
			Juego.getJuego().golpearFelix(this);
		}
		if (posicion.getY()==0) {
			mapa.borrarComponente(this);
		}
	}

}
