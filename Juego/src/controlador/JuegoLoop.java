package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import animaciones.AnimacionSubidaRalphRompiendo;
import graficador.modelo.Dibujable;
import graficador.vista.Graficador;
import juego.Juego;
import juego.Seccion;
import utils.Contador;
import vistas.PantallaJuego;
import vistas.PantallaMenu;
import vistas.PantallaRanking;

public class JuegoLoop implements Runnable {

	private Juego juego;
	private Seccion seccionActual;
	private Seccion seccionNueva;
	public JuegoLoop(Juego juego) {
		this.juego = juego;
	}
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		Contador timer = new Contador(10);
		System.out.println("Felix comienza en la posición " + juego.getFelix().getPosicion());
		seccionActual=Juego.getInstance().getMapa().getEdificio().getSeccionActual();
		
		Timer timerPintado = new Timer();
		timerPintado.schedule(new TimerTask() {
			public void run () {
				JuegoMain.getPantallaJuego().repaint();
			}
		}, 0,50);		
//		Thread t = new Thread(new AnimacionSubidaRalphRompiendo());
//		t.start();
//		try {
//			t.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		
//		
		
		Timer timerJuego = new Timer();
		
		timerJuego.schedule(new TimerTask() {
			public void run() {

				if (juego!=Juego.getInstance()) {
					timerJuego.cancel();
				}
				if (!juego.estaPausado()) {
					try {
						TimeUnit.MILLISECONDS.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					juego.actualizar();
				} else {
					if (juego.yaGano()) {
						juego.agregarRanking();
						//Graficador.mensaje("HAS GANADO!");
						System.out.println("entre a juego.yaGano()");
						PantallaRanking.getInstance().setVisible(true);
						JuegoMain.getPantallaJuego().setVisible(false);
						timerJuego.cancel();
					}
					if (!(juego.getTiempo() > 0 && juego.getFelix().getVidas() > 0)) {
						juego.agregarRanking();
						PantallaRanking.getInstance().setVisible(true);
						JuegoMain.getPantallaJuego().setVisible(false);
						timerJuego.cancel();
					}else {
						//Graficador.mensaje("PAUSA");
						//System.out.println("entre a tieneVidas()");
						System.out.print("");
					}
				}
			}
		}, 0,5);
		new AnimacionSubidaRalphRompiendo().run();
		
//		while (true) {
//			if (juego!=Juego.getInstance()) {
//				break;
//			}
//			if (!juego.estaPausado()) {
//				try {
//					TimeUnit.MILLISECONDS.sleep(5);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				juego.actualizar();
//			} else {
//				if (juego.yaGano()) {
//					juego.agregarRanking();
//					//Graficador.mensaje("HAS GANADO!");
//					System.out.println("entre a juego.yaGano()");
//					PantallaRanking.getInstance().setVisible(true);
//					break;
//				}
//				if (!(juego.getTiempo() > 0 && juego.getFelix().getVidas() > 0)) {
//					juego.agregarRanking();
//					PantallaRanking.getInstance().setVisible(true);
//					break;
//				}else {
//					//Graficador.mensaje("PAUSA");
//					//System.out.println("entre a tieneVidas()");
//					System.out.print("");
//				}
//			}
//		}
		

	}

}
