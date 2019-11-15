package animaciones;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import juego.Juego;
import personajes.Ralph;
import utils.Contador;
import utils.Posicion;

public class AnimacionSubidaRalph implements Runnable {

	private Timer timerDeSubida;
	private boolean llego = false;
	private int maxAltura;
	private Contador timer;
	private Ralph ralph;
	
	public AnimacionSubidaRalph() {
		ralph = Juego.getInstance().getRalph();
		timer = new Contador(200);
	}

	@Override
	public void run() {
		ralph.setSubida(true);
		maxAltura = ralph.getPosicion().getY() + 160;
		timerDeSubida = new Timer();
		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (timer.contar()) {
					ralph.swap();
					timer.resetear();
				}
				if (ralph.getPosicion().getY() < maxAltura) {
					Posicion posNueva = ralph.getPosicion().copia();
					posNueva.moverY(1);
					ralph.setPosicion(posNueva);
				} else {
					llego = true;
					timerDeSubida.cancel();
				}

			}
		}, 0, 10);
		while (!llego) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//timerDeSubida.cancel();
		ralph.setSubida(false);
	}

}
