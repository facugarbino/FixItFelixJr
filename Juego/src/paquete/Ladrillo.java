package paquete;

public class Ladrillo extends Componente {

	private int ANCHO;

	public Ladrillo(Posicion p, double frecuencia) {
		posicion = p;
		timer = new Contador(frecuencia);
	}

	public void comoAvanzo() {
		if (timer.contar()) {
			timer.resetear();
			posicion.moverY(-1);
		}
		if (mapa.estaFelix(posicion, ANCHO)) {
			Juego.getJuego().golpearFelix(this);
		}
		
	}

}
