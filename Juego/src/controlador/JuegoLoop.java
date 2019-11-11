package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import graficador.modelo.Dibujable;
import graficador.vista.Graficador;
import juego.AnimacionSubidaRalphRompiendo;
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
		(new Timer()).schedule(new TimerTask() {
			public void run () {
				JuegoMain.getPantallaJuego().repaint();
			}
		}, 0,10);
		Thread t = new Thread(new AnimacionSubidaRalphRompiendo());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		while (true) {
			if (!juego.estaPausado()) {
				
//				if (timer.contar()) {
//					timer.resetear();
//					// Grafica todo
//					//Graficador.refrescarTopDown(lista);
//					//Esto refresca la gráfica
//					JuegoMain.getPantallaJuego().repaint();
//				}
				//System.out.println("entre a !juego.estaPausado()");
				juego.actualizar();
//				seccionNueva = Juego.getInstance().getMapa().getEdificio().getSeccionActual();
//				if (seccionNueva!= seccionActual) {
//					seccionActual = seccionNueva;
//					Thread t = JuegoMain.getPantallaJuego().scrollHacia(seccionNueva.getPosicion());
//					try {
//						t.join();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					
//				}
				
				
			} else {
				if (juego.yaGano()) {
					String nombreJugador = preguntarNombre();					
					juego.setJugador(nombreJugador);
					juego.agregarRanking();
					//Graficador.mensaje("HAS GANADO!");
					System.out.println("entre a juego.yaGano()");
					break;
				}
				if (juego.getTiempo() > 0 && juego.getFelix().getVidas() > 0) {
					//Graficador.mensaje("PAUSA");
					//System.out.println("entre a tieneVidas()");
				} else {
					//Graficador.mensaje("GAME OVER");
					System.out.println("entre a perdio()");
					break;
				}
			}
		}
		PantallaRanking.getInstance().setVisible(true);
		JuegoMain.getPantallaJuego().setVisible(false);

	}
	private static String preguntarNombre() {
		return JOptionPane.showInputDialog(null, "Fix it Felix Jr.", "Inserte su Nombre: ",
				JOptionPane.QUESTION_MESSAGE);
	}

}
