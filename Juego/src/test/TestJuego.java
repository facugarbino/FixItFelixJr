package test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import juego.Juego;
import utils.Contador;

/**
 * Clase que permite testear el juego, con mensajes en la consola.
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class TestJuego {

	public static Juego j;

	public static void main(String[] args) {
		String nombre = preguntarNombre();
		while (nombre != null && nombre.equals("")) {
			nombre = preguntarNombre();
		}
		if (nombre == null) {
			System.exit(0);
		}
		j = Juego.getInstance();
		j.setJugador(nombre);
		loop();
	}

	private static String preguntarNombre() {
		return JOptionPane.showInputDialog(null, "Fix it Felix Jr.", "Inserte su Nombre: ",
				JOptionPane.QUESTION_MESSAGE);
	}

	private static void loop() {
		Contador timer = new Contador(100);
		System.out.println("Felix comienza en la posición " + j.getFelix().getPosicion());
		while (true) {
			if (!j.estaPausado()) {
				if (timer.contar()) {
					timer.resetear();
				}
				j.actualizar();
			} else {
				if (j.yaGano()) {
					break;
				}
				if (j.getTiempo() > 0 && j.getFelix().getVidas() > 0) {
					;
				} else {
					break;
				}
			}
		}
		System.out.println(j.getRanking().getTop5().toString());
		mostrarRanking();
	}

	private static void mostrarRanking() {
		JFrame frame = new JFrame("Ranking");
		frame.setSize(500, 100);
		JLabel label = new JLabel(j.getRanking().getTop5().toString());
		frame.add(label);
		frame.setVisible(true);
	}

}
