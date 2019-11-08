package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import graficador.modelo.Dibujable;
import graficador.vista.Graficador;
import juego.Juego;
import utils.Contador;
import vistas.PantallaJuego;
import vistas.PantallaMenu;
import vistas.PantallaRanking;

public class JuegoLoop implements Runnable {

	private Juego juego;
	public JuegoLoop(Juego juego) {
		this.juego = juego;
	}
	@Override
	public void run() {
		Contador timer = new Contador(100);
		System.out.println("Felix comienza en la posición " + juego.getFelix().getPosicion());
		List<Dibujable> lista = new ArrayList<Dibujable>();
		while (true) {
			if (!juego.estaPausado()) {
				if (timer.contar()) {
					timer.resetear();
					// Grafica todo
					lista = juego.getMapa().getComponentesDibujables();
					lista.add(juego.getRalph());
					lista.add(juego.getFelix());
					//Graficador.refrescarTopDown(lista);
					JuegoMain.getPantallaJuego().repaint();
				}
				juego.actualizar();
			} else {
				if (juego.yaGano()) {
					//Graficador.mensaje("HAS GANADO!");
					break;
				}
				if (juego.getTiempo() > 0 && juego.getFelix().getVidas() > 0) {
					//Graficador.mensaje("PAUSA");
				} else {
					//Graficador.mensaje("GAME OVER");
					break;
				}
			}
		}
		PantallaRanking.getInstance().setVisible(true);
		JuegoMain.getPantallaJuego().dispose();

	}

}
