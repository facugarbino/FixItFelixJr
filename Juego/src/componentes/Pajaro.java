package componentes;

import java.awt.Color;

import juego.Juego;
import juego.Mapa;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;

public class Pajaro extends Componente {

	private static final int ANCHO = 21;
	private static final int ALTO = 13;
	private boolean aleteo;
	//sus coordenadas indican el centro
	public Pajaro(Posicion p, int frecuencia, Orientacion o, Mapa m) {
		posicion = p;
		timer = new Contador(frecuencia);
		orientacion = o;
		caracter = 'P';
		color = Color.BLACK;
		mapa = m;
	}

	/**
	 * Hace la lógica para que el pájaro se mueva,
	 * de acuerdo al sentido en que está volando, y 
	 * cambia de sentido cuando llega a los extremos
	 */
	protected void comoAvanzo() {
		if (orientacion == Orientacion.IZQUIERDA) {
			if (posicion.getX() > 0) {
				posicion.moverX(-1);
				aleteo=!aleteo;
			} else {
				setOrientacion(Orientacion.DERECHA);
				comoAvanzo();
			}
		} else {
			if (posicion.getX() < (Mapa.ANCHO - (ANCHO))) {
				posicion.moverX(1);
				aleteo=!aleteo;
			} else {
				setOrientacion(Orientacion.IZQUIERDA);
				comoAvanzo();
			}
		}
		if (mapa.estaFelix(posicion, ANCHO, ALTO)) {
			Juego.getInstance().golpearFelix(this);
		}
	}
	
	public Orientacion getOrientacion() {
		return orientacion;
	}
	public boolean getAleteo() {
		return aleteo;
	}

}
