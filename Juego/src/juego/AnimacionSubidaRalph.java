package juego;

import java.util.Timer;
import java.util.TimerTask;

import personajes.Ralph;
import utils.Posicion;

public class AnimacionSubidaRalph implements Runnable {

	private Timer timerDeSubida;
	boolean llego = false;
	int maxAltura;

	@Override
	public void run() {
		Ralph ralph = Juego.getInstance().getRalph();
		ralph.setSubida(true);
		maxAltura = ralph.getPosicion().getY() + 160;
		timerDeSubida = new Timer();

		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getY() < maxAltura) {
					Posicion posNueva = ralph.getPosicion().copia();
					posNueva.moverY(1);
					ralph.setPosicion(posNueva);
					System.out.println("subio 1");
				} else {
					llego = true;
					timerDeSubida.cancel();
					System.out.println("llego");
				}

			}
		}, 0, 10);
		while (!llego) {
			System.out.println(llego);
		}
		//timerDeSubida.cancel();
		ralph.setSubida(false);
	}

}
