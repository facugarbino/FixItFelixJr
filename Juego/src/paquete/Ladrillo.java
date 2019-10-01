package paquete;

public class Ladrillo extends Componente {

	private int ANCHO = 2;
	private Mapa mapa;

	public Ladrillo(Posicion p, double velocidad, Mapa m) {
		posicion = p;
		timer = new Contador(velocidad);
		mapa = m;
	}

	public void comoAvanzo() {
		posicion.moverY(-1);
		//System.out.println("Un ladrillo avanza a la posicion " + posicion);
		if (mapa.estaFelix(posicion, ANCHO)) {
			System.out.println("Un ladrillo en la posicion " + posicion + " golpea a Felix");
			Juego.getJuego().golpearFelix(this);
		}

		if (posicion.getY()==0) {
			mapa.borrarComponente(this);
		}
	}

}
