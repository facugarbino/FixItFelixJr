package graficador.vista;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;

import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import juego.Edificio;
import juego.Juego;
import juego.Mapa;
import utils.Orientacion;

/*
 * Muestra en pantalla elementos. Por ejemplo, si tengo varios personajes (implementan la 
 * interfaz Dibujable), sólo necesito su posicón y un caracter representativo para mostrarlo
 * */
public class Graficador {
	private static final int ANCHO = Mapa.ANCHO;
	private static final int ALTO = Mapa.ALTO;
	private static JFrame frame = new JFrame("Visualización Fix It Felix");
	private static final int margen = (int) (Mapa.ANCHO / 3.0);
	private static Juego juego = Juego.getJuego();
	private static Graphics gr;
	private static boolean teclaPresionada;

	static {
		frame.setSize(Mapa.ANCHO + 2 * margen, Mapa.ALTO + margen);
		frame.setVisible(true);
		frame.setResizable(false);
		// Esto para que se cierre por completo el juego
		// al cerrar el JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gr = frame.getContentPane().getGraphics();
		// Investigamos y agregamos este Listener para mover a Felix
		// así se puede ver cómo funciona el resto del juego,
		// pasando de sección y de nivel.
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				//Usamos keyReleased porque 
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_P) {
					juego.pausar();
				} else {
					if (!juego.estaPausado()) {
						teclaPresionada = true;
						switch (key) {
						case (KeyEvent.VK_LEFT):
							juego.moverFelix(Orientacion.IZQUIERDA);
							break;
						case (KeyEvent.VK_RIGHT):
							juego.moverFelix(Orientacion.DERECHA);
							break;
						case (KeyEvent.VK_UP):
							juego.moverFelix(Orientacion.ARRIBA);
							break;
						case (KeyEvent.VK_DOWN):
							juego.moverFelix(Orientacion.ABAJO);
							break;
						case (KeyEvent.VK_H): {
							// hack para pasar de nivel con la h (para testear)
							juego.pasarDeNivel();
							break;
						}
						case (KeyEvent.VK_SPACE):
							juego.darMartillazo();
							break;
						}
					}
				}

			}

		});
	}

	public static void mensaje(String mensaje) {
		gr.clearRect(margen + (ANCHO / 2) - 2 - 100, ALTO / 2 - 100, ANCHO, ALTO / 2);
		gr.drawRect(margen + (ANCHO / 2) - 2 - 100, ALTO / 2 - 100, ANCHO, ALTO / 2);
		gr.drawString(mensaje, ANCHO / 2 + 20, ALTO / 2);
	}

	public static void refrescarTopDown(List<? extends Dibujable> listaDibujables) {
		Iterator var4 = listaDibujables.iterator();
		gr.clearRect(0, 0, ANCHO + 2 * margen, ALTO + 2 * margen);
		while (var4.hasNext()) {
			Dibujable i = (Dibujable) var4.next();
			InformacionDibujable id = i.getInformacionDibujable();
			gr.drawString(id.getRepresentacion().toString(), id.getX() + margen, ALTO - (id.getY()));
		}
		gr.drawString("Nivel: " + juego.getNroNivel(), 5, 20);
		gr.drawString("Tiempo: " + juego.getTiempo(), 60, 20);
		gr.drawString("Vidas: " + juego.getFelix().getVidas(), 5, 40);
		gr.drawString("Puntaje: " + (juego.getJugador().getPuntaje() + juego.getFelix().getPuntaje()), 60, 40);

	}
}