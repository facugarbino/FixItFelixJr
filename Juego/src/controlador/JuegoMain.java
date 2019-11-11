package controlador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import graficador.modelo.Dibujable;
import graficador.vista.Graficador;
import juego.Juego;
import utils.Contador;
import vistas.PantallaConfig;
import vistas.PantallaJuego;
import vistas.PantallaMenu;
import vistas.PantallaRanking;

public class JuegoMain {

	private static Juego juego;
	private static PantallaMenu menu;
	private static PantallaJuego pantallaJuego;
	public final static double MULTIPLICADOR = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/800;
	public final static double MULTIPLICADOR_MENU = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1400;

	
	public static PantallaJuego getPantallaJuego() {
		return pantallaJuego;
	}

	private static Thread t;
	private static boolean corriendo;

	public static void main(String[] args) {

		menu = PantallaMenu.getInstance();
		menu.setVisible(true);
	}

	public static void comenzarJuego() {
//		if (pantallaJuego == null) {
			Juego.reiniciarJuego();
			juego = Juego.getInstance();
			juego.setJugador("anonimo");
			juego.pasarDeNivel();
			int nivelAComenzar = (int) PantallaConfig.getInstance().getComboNivel().getSelectedItem();
			for (int i = 1; i < nivelAComenzar; i++) {
				juego.pasarDeNivel();
			}
			pantallaJuego = new PantallaJuego();
			t = new Thread(new JuegoLoop(juego));
			t.start();
			corriendo = true;
			// loop();
//		}
		pantallaJuego.setVisible(true);
		menu.setVisible(false);
	}

	private static String preguntarNombre() {
		return JOptionPane.showInputDialog(null, "Fix it Felix Jr.", "Inserte su Nombre: ",
				JOptionPane.QUESTION_MESSAGE);
	}

	private static void loop() {
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
					pantallaJuego.repaint();
					Graficador.refrescarTopDown(lista);
				}
				juego.actualizar();
			} else {
				if (juego.yaGano()) {
					Graficador.mensaje("HAS GANADO!");
					break;
				}
				if (juego.getTiempo() > 0 && juego.getFelix().getVidas() > 0) {
					Graficador.mensaje("PAUSA");
				} else {
					Graficador.mensaje("GAME OVER");
					break;
				}
			}
		}
		PantallaRanking.getInstance().setVisible(true);
	}

}
