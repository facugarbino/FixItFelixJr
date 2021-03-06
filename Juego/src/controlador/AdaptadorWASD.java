package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import juego.Juego;
import utils.Orientacion;

public class AdaptadorWASD extends KeyAdapter {
	private Juego juego;
	public AdaptadorWASD() {
		juego = Juego.getInstance();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// Usamos keyReleased porque
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_P) {
			juego.pausar();			
		} else if (key == KeyEvent.VK_M) {
			Audio.getInstance().setActivado();
			Audio.getInstance().fondo(Audio.getInstance().getActivado());
		} else {
			if (!juego.estaPausado()) {
				// teclaPresionada = true;
				switch (key) {
				case (KeyEvent.VK_A):
					juego.moverFelix(Orientacion.IZQUIERDA);
					break;
				case (KeyEvent.VK_D):
					juego.moverFelix(Orientacion.DERECHA);
					break;
				case (KeyEvent.VK_W):
					juego.moverFelix(Orientacion.ARRIBA);
					break;
				case (KeyEvent.VK_S):
					juego.moverFelix(Orientacion.ABAJO);
					break;
				case (KeyEvent.VK_H):
					// hack para pasar de nivel con la h (para testear)
					//solo usarlo cuando no está corriendo una animación
					juego.pasarDeNivelConHack();
					break;
				case (KeyEvent.VK_SPACE):
					juego.darMartillazo();
					break;
				}
			}
		}
	}
}
