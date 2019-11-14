package controlador;

import java.util.Timer;
import java.util.TimerTask;
import animaciones.AnimacionSubidaRalphRompiendo;
import juego.Juego;
import vistas.PantallaRanking;

public class JuegoLoop implements Runnable {

	private Juego juego;
	public JuegoLoop(Juego juego) {
		this.juego = juego;
	}
	
	@Override
	public void run() {
		Timer timerPintado = new Timer();
		Timer timerJuego = new Timer();
		System.out.println("Felix comienza en la posición " + juego.getFelix().getPosicion());
	
		timerPintado.schedule(new TimerTask() {
			public void run () {
				JuegoMain.getPantallaJuego().repaint();
			}
		}, 0,10);
		
		timerJuego.scheduleAtFixedRate(new TimerTask() {
			public void run() {

				if (juego!=Juego.getInstance()) {
					timerJuego.cancel();
				}
				if (!juego.estaPausado()) {
					juego.actualizar();
				} else {
					if (juego.yaGano()) {
						juego.agregarRanking();
						//Graficador.mensaje("HAS GANADO!");
						System.out.println("entre a juego.yaGano()");
						PantallaRanking.getInstance().setVisible(true);
						JuegoMain.getPantallaJuego().setVisible(false);
						timerJuego.cancel();
					} else if (!(juego.getTiempo() > 0 && juego.getFelix().getVidas() > 0)) {
						juego.agregarRanking();
						PantallaRanking.getInstance().setVisible(true);
						JuegoMain.getPantallaJuego().setVisible(false);
						timerJuego.cancel();
					}
				}
			}
		}, 0,5);
		new AnimacionSubidaRalphRompiendo().run();

	}

}
