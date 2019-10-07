package personajes;

import java.awt.Color;

import componentes.Ladrillo;
import componentes.Pajaro;
import graficador.modelo.InformacionDibujable;
import juego.Juego;
import juego.Seccion;
import utils.Contador;
import utils.Orientacion;
import utils.Posicion;
import ventanas.Ventana;

/**
 * Representa al FelixJr de un determinado nivel. 
 * 
 * @author Garbino y Rodríguez Murphy
 *
 */
public class FelixJr extends Personaje {
	private Contador timer;
	private int vidas;
	private Ventana ventanaActual;
	private boolean inmune;
	private long puntajeNivel;
	private long puntajeSeccion;

	public FelixJr(Posicion p, Ventana v, int vidas) {
		this.posicion = p;
		ventanaActual = v;
		puntajeNivel = 0;
		puntajeSeccion = 0;
		this.vidas = vidas;
		caracter = 'F';
		timer = new Contador(5000);
	}

	/**
	 * Martilla la ventana en la que se encuentra y acumula puntaje en caso de
	 * repararla
	 */
	public void darMartillazo() {
		System.out.println("Felix ha dado un martillazo!");
		if (ventanaActual.reparar()) {
			if (ventanaActual.getSeccion().estaSana()) {
				puntajeSeccion += 500;
				puntajeNivel += puntajeSeccion;
				puntajeSeccion = 0;
				System.out.println("Felix repara el ultimo panel de la seccion. Gana 500 puntos.");
			} else {
				puntajeSeccion += 100;
				System.out.println("Felix repara un panel. Gana 100 puntos.");
			}
		}
	}

	/**
	 * Se mueve en función del sentido indicado, siempre y cuando sea posible<br>
	 * (puede haber un obstáculo o caerse del mapa o de la sección)
	 */
	public void mover(Orientacion o) {
		Ventana v = ventanaActual.getVentana(o);
		if (v != null) {
			ventanaActual = v;
			switch (o) {
			case IZQUIERDA:
				posicion.moverX(-15);
				break;
			case DERECHA:
				posicion.moverX(15);
				break;
			case ABAJO:
				posicion.moverY(-30);
				break;
			case ARRIBA:
				posicion.moverY(30);
				break;
			}
			System.out.println("Felix se mueve a la posicion " + getPosicion());
			if (ventanaActual.estaRota()) {
				System.out.println(ventanaActual.getPosicion() + "La ventana esta rota");
			} else {
				System.out.println(ventanaActual.getPosicion() + "La ventana esta SANA");
			}
			comerPastel();
		} else {
			System.out.println("Felix no se puede mover a la " + o + ". Hay un obstaculo");
		}
	}

	/**
	 * Responde a que un ladrillo lo golpee, quitando una vida y reiniciando el
	 * nivel (o perdiendo)
	 */
	public void golpear(Ladrillo ladrillo) {
		if (!inmune) {
			System.out.println("Un ladrillo en la posicion " + ladrillo.getPosicion() + " golpea a Felix");
			if (--vidas > 0) {
				Juego.getJuego().reiniciarNivel(vidas);
				puntajeNivel = 0;
				puntajeSeccion = 0;
				System.out.println("Pierde una vida. (Quedan " + vidas + ")");
			} else {
				Juego.getJuego().perder(puntajeNivel + puntajeSeccion);
			}
		}
	}

	/**
	 * Responde a que un pájaro lo golpee, reiniciando la sección donde ese
	 * encuentra
	 */
	public void golpear(Pajaro pajaro) {
		if (!inmune) {
			Juego.getJuego().reiniciarSeccion();
			puntajeSeccion = 0;
			System.out.println("Felix es golpeado por un pájaro, reinicia la seccion");
		}
	}

	public int getVidas() {
		return vidas;
	}

	public void subirDeSeccion(Seccion s) {
		posicion = s.getVentanaInicial().getPosicion().copia();
		ventanaActual = s.getVentanaInicial();
		inmune = false;
	}
	
	public void comerPastel() {
		if (ventanaActual.hayPastel()) {
			ventanaActual.comerPastel();
			inmunizar();
		}
	}
	
	private void inmunizar() {
		timer.resetear();
		inmune = true;
		System.out.println("Felix come un pastel. Se inmuniza.");
	}

	/**
	 * Método que se ej ecuta constantemente para que Felix Jr. pierda su
	 * inmunización luego de un tiempo en caso de haberla obtenido, comiendo un
	 * pastel
	 */
	public void chequearInmunizacion() {
		if (inmune && timer.contar()) {
			timer.resetear();
			inmune = false;
			System.out.println("Felix deja de ser inmune");
		}
	}

	public long getPuntaje() {
		return (puntajeNivel + puntajeSeccion);
	}

	public void setPosicion(Posicion p) {
		posicion = p;
	}

	public void setVentana(Ventana v) {
		ventanaActual = v;
	}

	@Override
	public InformacionDibujable getInformacionDibujable() {
		Character c = ((inmune) ? 'ƒ' : 'F');
		return new InformacionDibujable(posicion.getX(), posicion.getY(), c, Color.BLUE);
	}
}
