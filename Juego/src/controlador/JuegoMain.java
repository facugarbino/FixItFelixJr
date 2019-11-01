package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import graficador.modelo.Dibujable;
import graficador.vista.Graficador;
import juego.Juego;
import utils.Contador;
import vistas.PantallaMenu;
import vistas.PantallaRanking;

public class JuegoMain {

	private static Juego juego;
	public static void main(String[] args) {
		
		PantallaMenu menu = new PantallaMenu();
		menu.setVisible(true);
		juego = Juego.getInstance();
		
		String nombre = preguntarNombre();
		while (nombre != null && nombre.equals("")) {
			nombre = preguntarNombre();
		}
		if (nombre == null) {
			System.exit(0);
		}
		juego = Juego.getInstance();
		juego.setJugador(nombre);
		loop();

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
