package personajes;

import componentes.Ladrillo;
import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import juego.Edificio;
import juego.Juego;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;

/**
 * Representa a Ralph, el demoledor,
 * el enemigo del personaje principal.
 * 
 * @author facu
 *
 */
public class Ralph extends Personaje {
	// El edificio mide 100x300
	// La ventana mide 10x20
	// Ralph mide en X: 25
	private static final int ANCHO = 25;
	private static final int VELOCIDAD = 5;

	private Orientacion orientacion;
	private int cantLadrillos;
	private int ladrillosTirados;
	private double velocidadLadrillo;
	private boolean estaTirando;
	private Contador timerFrecuencia;
	private Contador timerEntreLadrillos;
	private Contador timerCaminar;
	private int limiteIzquierdo;
	private int limiteDerecho;

	public Ralph(Posicion p, int cantLadrillos, double frecuencia, double velocidadLadrillo) {
		this.cantLadrillos = cantLadrillos;
		timerFrecuencia = new Contador(frecuencia);
		timerEntreLadrillos = new Contador(frecuencia/5);
		timerCaminar = new Contador(VELOCIDAD);
		this.velocidadLadrillo = velocidadLadrillo;
		posicion = p;
		estaTirando = false;
		caracter = 'R';
		limiteIzquierdo = Edificio.ANCHO/2;
		limiteDerecho = limiteIzquierdo + Edificio.ANCHO;
	}

	/**
	 * Ralph decide si se mueve y hacia dónde 
	 */
	public void mover() {
		if (!estaTirando) {
			if (timerCaminar.contar()) {
				timerCaminar.resetear();
				double random = Math.random();
				if (random < 0.45) {
					darPaso(Orientacion.IZQUIERDA);
				} else {
					if (random < 0.9) {
						darPaso(Orientacion.DERECHA);
					} else {
						orientacion = Orientacion.ABAJO;
						//System.out.println("Ralph mira para adelante");
					}
				}
			}
		}

	}

	/**
	 * Ralph da un paso en el sentido solicitado 
	 * si es posible y, si no, lo da
	 * en sentido contrario.
	 * 
	 */
	private void darPaso(Orientacion o) {
		//Hay que cambiar los valores de X porque Ralph se aleja muhcho
		if (o == Orientacion.IZQUIERDA && posicion.getX() > limiteIzquierdo) {
			orientacion = o;
			posicion.moverX(-3);
			//System.out.println("Ralph se mueve a la izquierda");
		} else {
			if (!(posicion.getX() < limiteDerecho)) {
				darPaso(Orientacion.IZQUIERDA);
			} else {
				orientacion = Orientacion.DERECHA;
				posicion.moverX(3);
				//System.out.println("Ralph se mueve a la derecha");
			}
		}
	}

	public void subirDeSeccion() {
		Posicion p = Juego.getJuego().getMapa().getEdificio().getSeccionActual().getVentanaInicial().getPosicion().copia();
		p.moverY(100);
		posicion = p;
		estaTirando = false;

	}

	/**
	 * Ralph decide si tirar un ladrillo
	 * @return <b>el ladrillo</b> que efectivamente fue tirado
	 * o <b>null</b> si no tiró ninguno
	 */
	public Ladrillo tirarLadrillo() {
		if (estaTirando) {
			// moverBrazos
			if (ladrillosTirados == 3 || cantLadrillos == 0) {
				// Deja de tirar
				estaTirando = false;
				//System.out.println("Ralph deja de tirar ladrillos");
			} else {
				if (timerEntreLadrillos.contar()) {
					ladrillosTirados++;
					cantLadrillos--;
					timerEntreLadrillos.resetear();
					System.out.println("Ralph tira una roca");
					return new Ladrillo(new Posicion(obtenerXRandom(), posicion.getY()-ANCHO/2), velocidadLadrillo,
							Juego.getJuego().getMapa());
				}
			}
		} else {
			if (cantLadrillos > 0 && timerFrecuencia.contar()) {
				estaTirando = true;
				orientacion = Orientacion.ABAJO;
				ladrillosTirados = 0;
				timerFrecuencia.resetear();
				//System.out.println("Ralph se pone a tirar ladrillos");
			}
		}
		return null;
	}

	/**
	 * 
	 * @return un valor de x aleatorio para la
	 * trayectoria de un ladrillo
	 */
	private int obtenerXRandom() {
		return (int) ((Math.floor(Math.random() * ANCHO) - (ANCHO / 2)) + (posicion.getX()));
	}
}
