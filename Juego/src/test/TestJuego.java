package test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import graficador.modelo.Dibujable;
import graficador.vista.Graficador;
import juego.Juego;
import ranking.HighScore;
import utils.Contador;

/**
 * Clase que permite testear el juego, con mensajes en la consola y un
 * graficador básico que permite jugar con las teclas.
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
		List<Dibujable> lista = new ArrayList<Dibujable>();
		while (true) {
			if (!j.estaPausado()) {
				if (timer.contar()) {
					/*
					 * Esto que está comentado serviría para que Felix se mueva y martille sólo.
					 * Pero mayoritariamente es golpeado por un ladrillo o tarda mucho en romper
					 * todos los paneles.
					 * 
					 * --------------------------------IMPORTANTE----------------------------------
					 * Entonces, hicimos que se pueda jugar desde el graficador, usando las teclas.
					 * ----------------------------------------------------------------------------
					 * 
					 * double rnd = Math.random(); 
					 * if (rnd < 0.25)
					 * 		j.getFelix().mover(Orientacion.IZQUIERDA); 
					 * else if (rnd < 0.50)
					 * 		j.getFelix().mover(Orientacion.DERECHA); 
					 * else if (rnd < 0.75)
					 * 		j.getFelix().mover(Orientacion.ABAJO); 
					 * else
					 * 		j.getFelix().mover(Orientacion.ARRIBA);
					 * for(int i = 0; i < 4; i++) { 
					 * 		j.darMartillazo(); 
					 * }
					 * 
					 */
					timer.resetear();
					// Grafica todo
					lista = j.getMapa().getComponentesDibujables();
					lista.add(j.getRalph());
					lista.add(j.getFelix());
					Graficador.refrescarTopDown(lista);
				}
				j.actualizar();
			} else {
				if (j.yaGano()) {
					Graficador.mensaje("HAS GANADO!");
					break;
				}
				if (j.getTiempo() > 0 && j.getFelix().getVidas() > 0) {
					Graficador.mensaje("PAUSA");
				} else {
					Graficador.mensaje("GAME OVER");
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
