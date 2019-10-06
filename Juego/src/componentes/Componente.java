package componentes;

import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import juego.Mapa;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;

public abstract class Componente implements Dibujable {
	protected Posicion posicion;
	protected Contador timer;
	protected Mapa mapa;
	protected Orientacion orientacion;
	Character caracter;

	public Posicion getPosicion() {
		return posicion;
	}

	public void avanzar() {
		if (timer.contar()) {
			timer.resetear();
			comoAvanzo();
		}
	}

	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(posicion.getX(), posicion.getY(), caracter);
	}

	protected abstract void comoAvanzo();

	protected void setOrientacion(Orientacion o) {
		orientacion = o;
	}
}
