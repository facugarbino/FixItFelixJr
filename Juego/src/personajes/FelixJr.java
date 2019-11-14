package personajes;


import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import componentes.Ladrillo;
import componentes.Pajaro;
import componentes.Puntaje;
import controlador.Audio;
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
	private Contador timerParpadeo;
	private int vidas;
	private Ventana ventanaActual;
	private boolean inmune;
	private long puntajeNivel;
	private long puntajeSeccion;
	private boolean saltando;
	private int saltoHorizontal;
	private int saltoVertical;
	private boolean estaMartillando;
	private Timer timerDeMartillo;
	private boolean martilloArriba;
	private boolean asustado;
	private boolean invisible;
	public static int ANCHO = 15;

	public FelixJr(Posicion p, Ventana v, int vidas) {
		this.posicion = p;
		ventanaActual = v;
		puntajeNivel = 0;
		puntajeSeccion = 0;
		this.vidas = vidas;
		caracter = 'F';
		timer = new Contador(5000);
		saltoHorizontal = (int)(Ventana.ANCHO*1.4);
		saltoVertical = 50;
		estaMartillando= false;
		timerDeMartillo = new Timer();
		martilloArriba = true;
	}

	public boolean estaInvisible() {
		return invisible;
	}
	public boolean estaSaltando() {
		return saltando;
	}
	
	public boolean estaMartillando() {
		return estaMartillando;
	}
	
	public void setMartillo() {
		estaMartillando = !estaMartillando;
	}
	public boolean martilloArriba() {
		return martilloArriba;
	}
	/**
	 * Martilla la ventana en la que se encuentra y acumula puntaje en caso de
	 * repararla
	 */
	public void darMartillazo() {
		System.out.println("Felix ha dado un martillazo!");
		if (ventanaActual.reparar()) {
			Audio.getInstance().arregloPanel();
			if (ventanaActual.getSeccion().estaSana()) {
				Audio.getInstance().seccionUp();
				Juego.getInstance().getMapa().agregarComponente(new Puntaje(500,posicion.copia(), Juego.getInstance().getMapa()));
				puntajeSeccion += 500;
				puntajeNivel += puntajeSeccion;
				puntajeSeccion = 0;
				System.out.println("Felix repara el ultimo panel de la seccion. Gana 500 puntos.");
			} else {
				Juego.getInstance().getMapa().agregarComponente(new Puntaje(100,posicion.copia(), Juego.getInstance().getMapa()));
				puntajeSeccion += 100;
				System.out.println("Felix repara un panel. Gana 100 puntos.");
			}
		}
		estaMartillando = true;
		martilloArriba=true;
		timerDeMartillo = new Timer();
		timerDeMartillo.schedule(new TimerTask() {
			int cant = 1;
			Timer timer = timerDeMartillo;
			@Override
			public void run() {
				if (cant==1) {
					martilloArriba = false;
					cant++;
				} else {
					martilloArriba = true;
					estaMartillando = false;
					timer.cancel();
				}
			}
		}, 50, 50);
		
	}

	/**
	 * Se mueve en función del sentido indicado, siempre y cuando sea posible<br>
	 * (puede haber un obstáculo o caerse del mapa o de la sección)
	 */
	public void mover(Orientacion o) {
		Ventana v = ventanaActual.getVentana(o);
		saltando=true;
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				saltando=false;
			}
		}, 200);
		if (v != null) {
			ventanaActual = v;
			posicion = v.getPosicion().copia();
			System.out.println("Felix se mueve a la posicion " + getPosicion());
			System.out.println(ventanaActual.estaRota() ? "La ventana está ROTA" : "La ventana está SANA");
			comerPastel();
		} else {
			Audio.getInstance().bloqueado();
			System.out.println("Felix no se puede mover a " + o + ". Hay un obstaculo");
		}
	}

	/**
	 * Responde a que un ladrillo lo golpee, quitando una vida y reiniciando el
	 * nivel (o perdiendo)
	 */
	public void golpear(Ladrillo ladrillo) {
		if (!inmune) {
			Audio.getInstance().choqueLadrillo();
			System.out.println("Un ladrillo en la posicion " + ladrillo.getPosicion() + " golpea a Felix");
			if (--vidas > 0) {
				Juego.getInstance().reiniciarNivel(vidas);
				puntajeNivel = 0;
				puntajeSeccion = 0;
				System.out.println("Pierde una vida. (Quedan " + vidas + ")");
			} else {
				Juego.getInstance().perder(puntajeNivel + puntajeSeccion);
			}
		}
	}

	/**
	 * Responde a que un pájaro lo golpee, reiniciando la sección donde ese
	 * encuentra
	 */
	public void golpear(Pajaro pajaro) {
		if (!inmune) {
			Audio.getInstance().choqueLadrillo();
			Juego.getInstance().reiniciarSeccion();
			puntajeSeccion = 0;
			System.out.println("Felix es golpeado por un pájaro, reinicia la seccion");
		}
	}

	public int getVidas() {
		return vidas;
	}

	public boolean estaInmune() {
		return inmune;
	}
	
	public void subirDeSeccion(Seccion s) {
		posicion = s.getVentanaInicial().getPosicion().copia();
		ventanaActual = s.getVentanaInicial();
		inmune = false;
	}

	public void comerPastel() {
		if (ventanaActual.hayPastel()) {
			Audio.getInstance().comerPastel();
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
//		if (inmune && timerParpadeo.contar()) {
//			timer.resetear();
//			inmune = false;
//			System.out.println("Felix deja de ser inmune");
//		}
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

	public Ventana getVentana() {
		return ventanaActual;
	}

	public void sacarPuntaje() {
		puntajeNivel=0;
		puntajeSeccion=0;
		
	}
	public boolean estaAsustado() {
		return asustado;
	}
	public void asustarse() {
		asustado=!asustado;
		
	}
	public void pausar() {
		timer.pausar();				
	}
}
