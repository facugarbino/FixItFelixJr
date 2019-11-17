package personajes;

import componentes.Ladrillo;
import juego.Edificio;
import juego.Juego;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;

/**
 * Representa a Ralph, el demoledor, el enemigo del personaje principal.
 * 
 * @author Garbino y Rodriguez Murphy
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
	private Contador timerSwap;
	private static final int LIMITE_IZQUIERDO = Edificio.ANCHO / 2;
	private static final int LIMITE_DERECHO = LIMITE_IZQUIERDO + Edificio.ANCHO - ANCHO * 2;
	// true y false representan dos estados
	// que van alternando
	private boolean swap;
	private boolean estaEnojado;
	private boolean estaMuerto;
	
	public Ralph(Posicion p, int cantLadrillos, int frecuencia, int velocidadLadrillo) {
		this.cantLadrillos = cantLadrillos;
		timerFrecuencia = new Contador(frecuencia);
		timerEntreLadrillos = new Contador(frecuencia / 5);
		timerCaminar = new Contador(VELOCIDAD);
		this.velocidadLadrillo = velocidadLadrillo;
		posicion = p;
		estaTirando = false;
		orientacion = Orientacion.ABAJO;
		timerSwap = new Contador(200);
		estaEnojado = false;
	}

	public boolean estaEnojado() {
		return estaEnojado;
	}

	public void setEnojado(boolean booleano) {
		estaEnojado = booleano;
	}

	public boolean estaMuerto() {
		return estaMuerto;
	}

	public void setMuerto(boolean booleano) {
		estaMuerto = booleano;
	}

	public void setOrientacion(Orientacion o) {
		orientacion = o;
	}

	public void setSubida(boolean booleano) {
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

	public boolean getSwap() {
		return swap;
	}

	public void swap() {
		swap = !swap;
	}

	/**
	 * Ralph decide si se mueve y hacia dónde
	 */
	public void mover() {
		if (timerSwap.contar()) {
			swap = !swap;
			timerSwap.resetear();
		}
		if (!estaTirando) {
			if (timerCaminar.contar()) {
				timerCaminar.resetear();
				double random = Math.random();
				if (orientacion == Orientacion.DERECHA) {
					if (random < 0.9) {
						darPaso(Orientacion.DERECHA);
					} else if (random<0.99){
						darPaso(Orientacion.IZQUIERDA);
					} else {
						orientacion=Orientacion.ABAJO;
					}
				} else if (orientacion == Orientacion.IZQUIERDA) {
					if (random < 0.9) {
						darPaso(Orientacion.IZQUIERDA);
					} else if (random<0.99){
						darPaso(Orientacion.DERECHA);
					} else {
						orientacion=Orientacion.ABAJO;
					}
				} else {
					if (random < 0.9) {
						;
					} else if (random < 0.95) {
						darPaso(Orientacion.IZQUIERDA);
					} else {
						darPaso(Orientacion.DERECHA);
					}
				}
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
				orientacion = Orientacion.ABAJO;
				ladrillosTirados = 0;
				// Cantidad variable de ladrillos que Ralph tira cada vez que se pone a tirar (entre 2 y 4 inclusive)
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

	public int getCantLadrillos() {
		return cantLadrillos;
	}

	public void setCantLadrillos(int nro) {
		cantLadrillos = nro;
	}

	public void setPosicion(Posicion p) {
		posicion = p;
	}

	public void pausar() {
		timerFrecuencia.pausar();
		timerCaminar.pausar();
		timerEntreLadrillos.pausar();
		timerSwap.pausar();
	}
}
