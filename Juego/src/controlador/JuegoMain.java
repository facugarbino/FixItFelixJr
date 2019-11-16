package controlador;

import java.awt.Toolkit;
import juego.Juego;
import ranking.Ranking;
import vistas.PantallaConfig;
import vistas.PantallaJuego;
import vistas.PantallaMenu;

public class JuegoMain {

	private static Juego juego;
	private static PantallaMenu menu;
	private static PantallaJuego pantallaJuego;
	public final static double MULTIPLICADOR = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 800;
	public final static double MULTIPLICADOR_MENU = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1400;
	private static Ranking ranking;

	public static PantallaJuego getPantallaJuego() {
		return pantallaJuego;
	}

	public static void main(String[] args) {
		ranking = new Ranking();
		menu = PantallaMenu.getInstance();
		menu.setVisible(true);
	}

	public static Ranking getRanking() {
		return ranking;
	}

	public static void comenzarJuego() {
		Juego.reiniciarJuego();
		juego = Juego.getInstance();
		juego.setJugador("anonimo");
		juego.pasarDeNivel();
		int nivelAComenzar = (int) PantallaConfig.getInstance().getComboNivel().getSelectedItem();
		for (int i = 1; i < nivelAComenzar; i++) {
			juego.pasarDeNivel();
		}
		pantallaJuego = new PantallaJuego();
		new Thread(new JuegoLoop(juego)).start();
		menu.setVisible(false);
		pantallaJuego.setVisible(true);

	}

}
