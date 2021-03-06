package componentes;

import java.util.Timer;
import java.util.TimerTask;

import juego.Juego;
import juego.Mapa;
import utils.Contador;
import utils.Posicion;
/**
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class Ladrillo extends Componente {

	private static final int ANCHO = 12;
	private static final int ALTO = 8;
	private Mapa mapa;
	private boolean swap;
	private Timer timerSwap;

	public Ladrillo(Posicion p, int velocidad, Mapa m) {
		posicion = p;
		timer = new Contador(velocidad);
		mapa = m;
		timerSwap = new Timer();
		timerSwap.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				swap = !swap;
			}
		},0,300);
	}

	/**
	 * Tiene la lógica para que un ladrillo avance
	 * (caiga verticalmente) a determinada velocidad
	 */
	protected void comoAvanzo() {
		posicion.moverY(-1);
		//System.out.println("Un ladrillo avanza a la posicion " + posicion);
		if (mapa.estaFelix(posicion, ANCHO, ALTO)) {
			Juego.getInstance().golpearFelix(this);
		}
		if (posicion.getY()==0) {
			timerSwap.cancel();
			mapa.borrarComponente(this);
		}
	}

	public boolean swap() {
		return swap;
	}
}
