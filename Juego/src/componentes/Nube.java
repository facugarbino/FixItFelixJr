package componentes;

import java.awt.Color;

import juego.Edificio;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;

public class Nube extends Componente {

	private int ANCHO = 20;

	public Nube(Posicion p, int frecuencia) {
		posicion = p;
		timer = new Contador(frecuencia);
		orientacion = Orientacion.DERECHA;
		caracter = '∞';
		color = Color.GRAY;
	}
	/**
	 * Hace la lógica para que la nube se mueva de extremo a extremo
	 */
	protected void comoAvanzo() {
			if (orientacion == Orientacion.IZQUIERDA) {
				if (posicion.getX() > 0) {
					posicion.moverX(-1);
				} else {
					setOrientacion(Orientacion.DERECHA);
					comoAvanzo();
				}
			} else {
				if (posicion.getX() < (200)) {
					posicion.moverX(1);
				} else {
					setOrientacion(Orientacion.IZQUIERDA);
					comoAvanzo();
				}
			}
			if (posicion.getX()>Edificio.ANCHO/2 && posicion.getX()<(Edificio.ANCHO/2+Edificio.ANCHO)) {
				caracter = ' ';
			} else {
				 caracter = '∞';
			}
	}

}
