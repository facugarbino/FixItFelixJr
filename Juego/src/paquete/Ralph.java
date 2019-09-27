package paquete;


public class Ralph extends Personaje {
	// El edificio mide 100x300
	// La ventana mide 10x20
	// Ralph mide en X: 25
	private int cantLadrillos;
	private double velocidadLadrillo;

	public Ralph(int cantLadrillos, double frecuencia, double velocidadLadrillo) {
		timer = new Contador(frecuencia);
		this.velocidadLadrillo = velocidadLadrillo;
	}

	public void mover() {

	}

	public Ladrillo tirarLadrillo() {
		if (timer.contar()) {
			timer.resetear();
			return new Ladrillo(new Posicion(obtenerXRandom(), this.getPosicion().getY()), velocidadLadrillo);
		} else {
			return null;
		}
	}

	private int obtenerXRandom() {
		return (int) ((Math.floor(Math.random() * 25) - (25 / 2)) + (this.getPosicion().getX()));
	}
}
