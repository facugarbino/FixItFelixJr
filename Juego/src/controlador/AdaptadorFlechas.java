package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import juego.Juego;
import utils.Orientacion;

public class AdaptadorFlechas extends KeyAdapter {
	private Juego juego;

	public AdaptadorFlechas() {
		juego = Juego.getInstance();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Usamos keyReleased porque
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_P) {
			juego.pausar();
			juego.graficarPausar();
		} else {
			if (!juego.estaPausado()) {
				// teclaPresionada = true;
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
				case (KeyEvent.VK_H):
					// hack para pasar de nivel con la h (para testear)
					juego.pasarDeNivel();
					break;
				case (KeyEvent.VK_SPACE):
					juego.darMartillazo();
					break;
				case (KeyEvent.VK_R):
					JuegoMain.getPantallaJuego().scrollearUp(20);
					break;
				case (KeyEvent.VK_D):
					JuegoMain.getPantallaJuego().scrollearUp(-20);
					break;
				}
			}
		}
	}
}
