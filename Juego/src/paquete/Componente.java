package paquete;

public abstract class Componente {
	protected Posicion posicion;
	protected Contador timer;
	protected Mapa mapa;
	protected Orientacion orientacion;

	public Posicion getPosicion() {
		return posicion;
	}

	public void avanzar() {
		if (timer.contar()) {
			timer.resetear();
			comoAvanzo();
		}
	}

	protected abstract void comoAvanzo();

	protected void setOrientacion(Orientacion o) {
		orientacion = o;
	}
}
