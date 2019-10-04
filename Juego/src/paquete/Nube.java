package paquete;

public class Nube extends Componente {

	private int ANCHO = 30;

	public Nube(Posicion p, double frecuencia) {
		posicion = p;
		timer = new Contador(frecuencia);
		orientacion = Orientacion.DERECHA;
		caracter = 'N';
	}

	/**
	 * Hace la lógica para que la nube se mueva de extremo a extremo
	 */
	public void comoAvanzo() {
			if (orientacion == Orientacion.IZQUIERDA) {
				if (posicion.getX() > 0) {
					posicion.moverX(-1);
				} else {
					setOrientacion(Orientacion.DERECHA);
					comoAvanzo();
				}
			} else {
				if (posicion.getX() < (200 - (ANCHO))) {
					posicion.moverX(1);
				} else {
					setOrientacion(Orientacion.IZQUIERDA);
					comoAvanzo();
				}
			}
	}

}
