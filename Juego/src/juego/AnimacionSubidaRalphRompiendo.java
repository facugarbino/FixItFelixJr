package juego;

import java.util.Timer;
import java.util.TimerTask;
import personajes.Ralph;
import utils.Orientacion;
import utils.Posicion;

public class AnimacionSubidaRalphRompiendo implements Runnable {

	private Timer timerDeSubida;
	private Timer timerDeEnojo;
	boolean llego = false;
	boolean termino = false;
	boolean llegoBase = false;
	int maxAltura;
	int maxBase;

	@Override
	public void run() {
		Ralph ralph = Juego.getInstance().getRalph();
		maxAltura = 166;
		ralph.setOrientacion(Orientacion.IZQUIERDA);
		ralph.setPosicion(new Posicion(Edificio.ANCHO * 2, 0));
		timerDeSubida = new Timer();
		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getX() > (Edificio.ANCHO+40)) {
					ralph.getPosicion().moverX(-1);;
				} else {
					llegoBase = true;
					System.out.println("llego a la base");
					ralph.setSubida(true);
					timerDeSubida.cancel();
				}
			}
		}, 0, 10);
		while(!llegoBase) {
			System.out.println("aun no llego a la base");
		}
		timerDeSubida = new Timer();
		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getY() < (maxAltura/3)) {
					ralph.getPosicion().moverY(1);
				} else {
					llego = true;
					timerDeSubida.cancel();
					System.out.println("llego al primer piso");
				}
			}
		}, 0, 10);
		while(!llego) {
			System.out.println("aun no llego al primer piso");
		}
		timerDeSubida = new Timer();
		llego = false;
		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getX() > 140) {
					ralph.getPosicion().moverX(-1);;
				} else {
					llego = true;
					timerDeSubida.cancel();
					System.out.println("llego al borde");
				}
			}
		}, 0, 10);
		while(!llego) {
			System.out.println("aun no llego al borde");
		}
		timerDeSubida = new Timer();
		llego = false;
		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getY() < (2*(maxAltura/3))) {
					ralph.getPosicion().moverY(1);;
				} else {
					llego = true;
					timerDeSubida.cancel();
					System.out.println("llego al segundo piso");
				}
			}
		}, 0, 10);
		while(!llego) {
			System.out.println("aun no llego al segundo piso");
		}
		timerDeSubida = new Timer();
		llego = false;
		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getX() < 180) {
					ralph.getPosicion().moverX(1);
				} else {
					llego = true;
					timerDeSubida.cancel();
					System.out.println("llego al centro del 2do piso");
				}
			}
		}, 0, 10);
		while(!llego) {
			System.out.println("aun no llego al centro del 2do piso");
		}
		timerDeSubida = new Timer();
		llego = false;
		timerDeSubida.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getY() < maxAltura) {
					ralph.getPosicion().moverY(1);;
				} else {
					llego = true;
					timerDeSubida.cancel();
					System.out.println("llego al tercer piso");
				}
			}
		}, 0, 10);
		while(!llego) {
			System.out.println("aun no llego al 3er piso");
		}
		ralph.setSubida(false);
		timerDeEnojo = new Timer();
		timerDeEnojo.schedule(new TimerTask() {
			int cant = 1;
			@Override
			public void run() {
				if (cant == 1) {
					ralph.setEnojado(true);
					cant ++;
				} else {
					ralph.setEnojado(false);
					timerDeEnojo.cancel();
					termino = true;
				}
			}
		}, 0, 2000);
		while (!termino) {
			System.out.println("ralph sigue enojado");
		}
	}
}
