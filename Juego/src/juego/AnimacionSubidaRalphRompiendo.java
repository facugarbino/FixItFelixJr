package juego;

import java.util.Timer;
import java.util.TimerTask;
import personajes.Ralph;
import utils.Orientacion;
import utils.Posicion;

public class AnimacionSubidaRalphRompiendo implements Runnable {

	private Timer timerDeSubida;
	boolean llego = false;
	boolean llegoBase = false;
	int maxAltura;
	int maxBase;

	@Override
	public void run() {
		Ralph ralph = Juego.getInstance().getRalph();
		maxAltura = 166;
		ralph.setOrientacion(Orientacion.IZQUIERDA);
		ralph.setPosicion(new Posicion(Edificio.ANCHO*2,0));
		timerDeSubida = new Timer();

		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (!llegoBase) {
					if (!(ralph.getPosicion().getX() == Edificio.ANCHO + 40)) {
						ralph.getPosicion().moverX(-1);
						
					} else {
						llegoBase = true;
						ralph.setSubida(true);
					}
				} else {
					if (!(ralph.getPosicion().getY() == maxAltura)) {
						if (ralph.getPosicion().getY() < (maxAltura / 3)) {
							ralph.getPosicion().moverY(1);
						} else {
							if (ralph.getPosicion().getX() > 160) {
								ralph.getPosicion().moverX(-1);
							}
						}
						if (ralph.getPosicion().getY() < 2 * (maxAltura / 3)) {
							ralph.getPosicion().moverY(1);
						} else {
							if (ralph.getPosicion().getX() < 210) {
								ralph.getPosicion().moverX(1);
							}
						}
						if (ralph.getPosicion().getY() < maxAltura) {
							ralph.getPosicion().moverY(1);
						}
					} else {
						ralph.setSubida(false);
						llego = true;
					}
				}

			}
		}, 0, 50);
		while (!llego);
		timerDeSubida.cancel();
		ralph.setSubida(false);
	}

}
