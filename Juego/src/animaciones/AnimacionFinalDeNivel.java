package animaciones;

import java.util.Timer;
import java.util.TimerTask;

import juego.Edificio;
import juego.Juego;
import personajes.FelixJr;
import personajes.Ralph;
import utils.Orientacion;
import utils.Posicion;

public class AnimacionFinalDeNivel implements Runnable {

	private FelixJr felix = Juego.getInstance().getFelix();
	private Ralph ralph = Juego.getInstance().getRalph();
	private Timer felixCamina;
	private Timer ralphSalta;
	private boolean termino = false;

	public void run() {
		Juego.getInstance().pausar();
		ralph.setPosicion(new Posicion(Edificio.ANCHO/2+155,495));
		felix.setPosicion(new Posicion(Edificio.ANCHO/2+75,495));
		felix.setMartillo();
		felixCamina = new Timer();
		felixCamina.schedule(new TimerTask() {
			int cant = 0;

			@Override
			public void run() {
				if (cant < 70) {
					felix.getPosicion().moverX(1);
					cant ++;
				} else {
					felix.setMartillo();
					System.out.println("felix se mueve hacia ralph");
					termino = true;
					felixCamina.cancel();
				}
			}
		}, 0, 30);
		while (!termino) {
			System.out.println("felix aun no alcanzo a ralph");
		}
		termino = false;

		ralph.setOrientacion(Orientacion.ABAJO);
		ralph.setEnojado(true);
		ralphSalta = new Timer();
		ralphSalta.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getX() < 300) {
					ralph.getPosicion().moverY(1);
					ralph.getPosicion().moverX(5);
				} else {
					System.out.println("ya salto");
					termino = true;
					ralphSalta.cancel();
				}
			}
		}, 0, 10);
		while (!termino) {
			System.out.println("aun no salto");
		}
		termino = false;
		
		ralphSalta = new Timer();
		ralphSalta.schedule(new TimerTask() {
			@Override
			public void run() {
				if (ralph.getPosicion().getY() > -72) {
					ralph.getPosicion().moverY(-1);
				} else {
					System.out.println("ya llego al piso");
					termino = true;
					ralph.setEnojado(false);
					ralph.setOrientacion(Orientacion.IZQUIERDA);
					ralphSalta.cancel();
				}
			}
		}, 0, 10);
		while (!termino) {
			System.out.println("aun no llego al piso");
		}
		felix.setPosicion(felix.getVentana().getPosicion().copia());
//		felix.setPosicion(Juego.getInstance().getMapa().getEdificio().getSeccionActual().getVentanaInicial().getPosicion().copia());
		Juego.getInstance().pausar();
	}
}
