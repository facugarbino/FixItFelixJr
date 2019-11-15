package animaciones;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import juego.Edificio;
import juego.Juego;
import personajes.Ralph;
import utils.Orientacion;
import utils.Posicion;

public class AnimacionSubidaRalphRompiendo implements Runnable {

	private Timer timer;
	private boolean llego;
	private int maxAltura;
	private Ralph ralph;
	private Timer timerSwap;
	
	public AnimacionSubidaRalphRompiendo() {
		ralph = Juego.getInstance().getRalph();
		timerSwap = new Timer();
	}

	@Override
	public void run() {
		Juego.getInstance().pausar();
		timerSwap.schedule(new TimerTask() {
			public void run() {
				ralph.swap();
			}
		}, 0,200);
		maxAltura = 166;
		llego=false;
		ralph.setOrientacion(Orientacion.IZQUIERDA);
		ralph.setPosicion(new Posicion(Edificio.ANCHO * 2, 0));
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getX() > (Edificio.ANCHO+40)) {
					ralph.getPosicion().moverX(-1);;
				} else {
					llego = true;
					System.out.println("llego a la base");
					ralph.setSubida(true);
					timer.cancel();
				}
			}
		}, 0, 10);
		while(!llego) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("aun no llego a la base");
		}
		llego=false;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getY() < (maxAltura/3)) {
					ralph.getPosicion().moverY(1);
				} else {
					llego = true;
					timer.cancel();
					System.out.println("llego al primer piso");
				}
			}
		}, 0, 10);
		while(!llego) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("aun no llego al primer piso");
		}
		timer = new Timer();
		llego = false;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getX() > 140) {
					ralph.getPosicion().moverX(-1);;
				} else {
					llego = true;
					timer.cancel();
					System.out.println("llego al borde");
				}
			}
		}, 0, 10);
		while(!llego) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("aun no llego al borde");
		}
		timer = new Timer();
		llego = false;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getY() < (2*(maxAltura/3))) {
					ralph.getPosicion().moverY(1);;
				} else {
					llego = true;
					timer.cancel();
					System.out.println("llego al segundo piso");
				}
			}
		}, 0, 10);
		while(!llego) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("aun no llego al segundo piso");
		}
		timer = new Timer();
		llego = false;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getX() < 180) {
					ralph.getPosicion().moverX(1);
				} else {
					llego = true;
					timer.cancel();
					System.out.println("llego al centro del 2do piso");
				}
			}
		}, 0, 10);
		while(!llego) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("aun no llego al centro del 2do piso");
		}
		timer = new Timer();
		llego = false;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getY() < maxAltura) {
					ralph.getPosicion().moverY(1);;
				} else {
					llego = true;
					timer.cancel();
					System.out.println("llego al tercer piso");
				}
			}
		}, 0, 10);
		while(!llego) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("aun no llego al 3er piso");
		}
		llego=false;
		ralph.setSubida(false);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			int cant = 1;
			@Override
			public void run() {
				if (cant == 1) {
					ralph.setEnojado(true);
					cant ++;
				} else {
					ralph.setEnojado(false);
					timer.cancel();
					llego = true;
				}
			}
		}, 0, 2000);
		while (!llego) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("ralph sigue enojado");
		}
		timerSwap.cancel();
		Juego.getInstance().pausar();
	}
}
