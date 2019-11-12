package personajes;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import componentes.Ladrillo;
import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import juego.AnimacionSubidaRalph;
import juego.Edificio;
import juego.Juego;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;
import ventanas.Ventana;

/**
 * Representa a Ralph, el demoledor, el enemigo del personaje principal.
 * 
 * @author facu
 *
 */
public class Ralph extends Personaje {
	// El edificio mide 100x300
	// La ventana mide 10x20
	// Ralph mide en X: 25
	private static final int ANCHO = 50;
	private static final int VELOCIDAD = 50;

	private Orientacion orientacion;
	private int cantLadrillos;
	private int ladrillosTirados;
	private int ladrillosQueTieneQuetirar;
	private int velocidadLadrillo;
	private boolean estaTirando;
	private Contador timerFrecuencia;
	private Contador timerEntreLadrillos;
	private Contador timerCaminar;
	private static final int LIMITE_IZQUIERDO = Edificio.ANCHO / 2;
	private static final int LIMITE_DERECHO = LIMITE_IZQUIERDO + Edificio.ANCHO - ANCHO * 2;
	// true y false representan dos posiciones
	// para tirar ladrillos (en las q va alternando)
	private boolean posicionDeTiro;
	private Timer timerDeTiro;
	private boolean estaSubiendo;
	private boolean estaEnojado;

	public Ralph(Posicion p, int cantLadrillos, int frecuencia, int velocidadLadrillo) {
		this.cantLadrillos = cantLadrillos;
		timerFrecuencia = new Contador(frecuencia);
		timerEntreLadrillos = new Contador(frecuencia / 5);
		timerCaminar = new Contador(VELOCIDAD);
		this.velocidadLadrillo = velocidadLadrillo;
		posicion = p;
		estaTirando = false;
		caracter = 'R';
		orientacion = Orientacion.ABAJO;
		estaSubiendo = false;
		timerDeTiro = new Timer();
		boolean estaEnojado = false;

		timerDeTiro.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				posicionDeTiro = !posicionDeTiro;
			}
		}, 0, 200);
	}

	public boolean estaEnojado() {
		return estaEnojado;
	}
	
	public void setEnojado(boolean booleano) {
		estaEnojado = booleano;
	}
	
	public void setOrientacion(Orientacion o) {
		orientacion = o;
	}
	public void setSubida(boolean booleano) {
		estaSubiendo = booleano;
		if (booleano) {
			orientacion = Orientacion.ARRIBA;
		} else {
			orientacion = Orientacion.ABAJO;
		}
	}

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public boolean estaTirandoLadrillos() {
		return estaTirando;
	}

	public boolean getPosicionDeTiro() {
		return posicionDeTiro;
	}

	/**
	 * Ralph decide si se mueve y hacia dónde
	 */
	public void mover() {
		caracter = caracterSegunOrientacion(orientacion);
		if (!estaTirando) {
			if (timerCaminar.contar()) {
				timerCaminar.resetear();
				double random = Math.random();
				if (orientacion == Orientacion.DERECHA) {
					if (random < 0.8) {
						darPaso(Orientacion.DERECHA);
					} else {
						darPaso(Orientacion.IZQUIERDA);
					}
				} else if (orientacion == Orientacion.IZQUIERDA) {
					if (random < 0.8) {
						darPaso(Orientacion.IZQUIERDA);
					} else {
						darPaso(Orientacion.DERECHA);
					}
				} else {
					if (random < 0.4) {

					} else if (random < 0.7) {
						darPaso(Orientacion.IZQUIERDA);
					} else {
						darPaso(Orientacion.DERECHA);
					}
				}

//				if (random < 0.45) {
//					darPaso(Orientacion.IZQUIERDA);
//				} else {
//					if (random < 0.9) {
//						darPaso(Orientacion.DERECHA);
//					} else {
//						orientacion = Orientacion.ABAJO;
//						// System.out.println("Ralph mira para adelante");
//					}
//				}

			}
		}
	}

	/**
	 * Ralph da un paso en el sentido solicitado si es posible y, si no, lo da en
	 * sentido contrario.
	 * 
	 */
	private void darPaso(Orientacion o) {
		// Hay que cambiar los valores de X porque Ralph se aleja muhcho
		if (o == Orientacion.IZQUIERDA && posicion.getX() > LIMITE_IZQUIERDO) {
			orientacion = o;
			posicion.moverX(-3);
			// System.out.println("Ralph se mueve a la izquierda");
		} else {
			if (!(posicion.getX() < LIMITE_DERECHO)) {
				darPaso(Orientacion.IZQUIERDA);
			} else {
				orientacion = Orientacion.DERECHA;
				posicion.moverX(3);
				// System.out.println("Ralph se mueve a la derecha");
			}
		}
	}

	private Character caracterSegunOrientacion(Orientacion o) {
		switch (o) {
		case DERECHA:
			return 'R';
		case IZQUIERDA:
			return 'Я';
		default:
			return 'A';
		}
	}

	public void subirDeSeccion() {
//		Posicion p = Juego.getInstance().getMapa().getEdificio().getSeccionActual().getVentanaInicial().getPosicion()
//				.copia();
//		p.moverY(160);
//		posicion = p;
		estaTirando = false;
		

	}

	/**
	 * Ralph decide si tirar un ladrillo
	 * 
	 * @return <b>el ladrillo</b> que efectivamente fue tirado o <b>null</b> si no
	 *         tiró ninguno
	 */
	public Ladrillo tirarLadrillo() {
		if (estaTirando) {
			// moverBrazos
			if (ladrillosTirados == ladrillosQueTieneQuetirar || cantLadrillos == 0) {
				// Deja de tirar
				estaTirando = false;
				// timerDeTiro.cancel();
				// System.out.println("Ralph deja de tirar ladrillos");
			} else {
				if (timerEntreLadrillos.contar()) {
					ladrillosTirados++;
					cantLadrillos--;
					timerEntreLadrillos.resetear();
					System.out.println("Ralph tira una roca");
					return new Ladrillo(new Posicion(obtenerXRandom(), posicion.getY() + 7), velocidadLadrillo,
							Juego.getInstance().getMapa());
				}
			}
		} else {
			if (cantLadrillos > 0 && timerFrecuencia.contar()) {
				estaTirando = true;
//				timerDeTiro.schedule(new TimerTask() {
//					@Override
//					public void run() {
//						posicionDeTiro = !posicionDeTiro;
//					}
//				}, 300);
				orientacion = Orientacion.ABAJO;
				ladrillosTirados = 0;
				// Cantidad variable de ladrillos que Ralph tira cada vez que se pone a tirar
				ladrillosQueTieneQuetirar = (int) (Math.random() * 3 + 2);
				timerFrecuencia.resetear();
				// System.out.println("Ralph se pone a tirar ladrillos");
			}
		}
		return null;
	}

	/**
	 * 
	 * @return un valor de x aleatorio para la trayectoria de un ladrillo
	 */
	private int obtenerXRandom() {
		return (int) ((Math.floor(Math.random() * ANCHO)) + (posicion.getX()));
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		return new InformacionDibujable(posicion.getX(), posicion.getY(), caracter, Color.BLACK);
	}

	public int getCantLadrillos() {
		return cantLadrillos;
	}

	public void setCantLadrillos(int nro) {
		cantLadrillos = nro;
	}

	public void setPosicion(Posicion p) {
		posicion = p;
	}
}
