package test;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import graficador.modelo.Dibujable;
import graficador.vista.Graficador;
import juego.Juego;
import ranking.HighScore;
import utils.Contador;

/**
 * Clase que permite testear el juego, con mensajes en la consola y un
 * graficador básico que permite jugar con las teclas.
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class TestJuego {

	public static Juego j;

	public static void main(String[] args) {
		Juego.crearJuego("Facundo");
		j = Juego.getJuego();
		loop();
	}

	private static void loop() {
		Contador timer = new Contador(10);
		System.out.println("Felix comienza en la posición " + j.getFelix().getPosicion());
		List<Dibujable> lista = new ArrayList<Dibujable>();
		while (true) {
			if (!j.estaPausado()) {
				if (timer.contar()) {
					/*
					 * Esto que está comentado serviría para que Felix se mueva y martille sólo.
					 * Pero mayoritariamente es golpeado por un ladrillo o tarda mucho en romper
					 * todos los paneles.
					 * 
					 * ---------------------IMPORTANTE--------------------- Entonces, investigamos e
					 * hicimos que se pueda jugar desde el graficador, usando las teclas.
					 * ----------------------------------------------------
					 * 
					 * double rnd = Math.random(); if (rnd < 0.25)
					 * j.getFelix().mover(Orientacion.IZQUIERDA); else if (rnd < 0.50)
					 * j.getFelix().mover(Orientacion.DERECHA); else if (rnd < 0.75)
					 * j.getFelix().mover(Orientacion.ABAJO); else
					 * j.getFelix().mover(Orientacion.ARRIBA);
					 * System.out.println("Felix se ha movido a " + j.getFelix().getPosicion()); for
					 * (int i = 0; i < 4; i++) { j.darMartillazo(); }
					 */
					timer.resetear();
					// Grafica todo
					lista = j.getMapa().getComponentesDibujables();
					lista.add(j.getRalph());
					lista.add(j.getFelix());
					Graficador.refrescarTopDown(lista);
				}
				j.hacerTodo();
			} else {
				if (j.yaGano()) {
					Graficador.mensaje("HAS GANADO!");
					break;
				}
				if (j.getTiempo() > 0 && j.getFelix().getVidas() > 0) {
					Graficador.mensaje("PAUSA");
				} else {
					Graficador.mensaje("GAME OVER");
					break;
				}
			}
		}
		System.out.println(j.getRanking().getTop5().toString());
		mostrarRanking();
	}
	
	private static void mostrarRanking() {
		JFrame frame = new JFrame("Ranking");
		frame.setSize(500,100);
		JLabel label = new JLabel(j.getRanking().getTop5().toString());
		frame.add(label);
		frame.setVisible(true);
	}

}
