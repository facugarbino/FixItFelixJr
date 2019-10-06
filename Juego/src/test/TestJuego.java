package test;

import java.util.ArrayList;
import java.util.List;

import graficador.modelo.Dibujable;
import graficador.vista.Graficador;
import juego.Juego;
import utils.Contador;

public class TestJuego {

	public static Juego j;

	public static void main(String[] args) {
		Juego.crearJuego("Facundo");
		j = Juego.getJuego();
		loop();
	}

	static void loop() {
		Contador timer = new Contador(50);
		System.out.println("Felix comienza en la posición " + j.getFelix().getPosicion());
		List<Dibujable> lista = new ArrayList<Dibujable>(); 
		while (true) {
			if (!j.estaPausado()) {
				if (timer.contar()) {
					/*
					 * Esto que está comentado serviría
					 * para que Felix se mueva y martille sólo.
					 * Pero mayoritariamente es golpeado por un ladrillo
					 * o tarda mucho en romper todos los paneles.
					 * 
					 * ---------------------IMPORTANTE---------------------
					 * Entonces, investigamos e hicimos que se pueda jugar
					 * desde el graficador, usando las teclas.
					 * ----------------------------------------------------
					 * 
					double rnd = Math.random();
					if (rnd < 0.25)
						j.getFelix().mover(Orientacion.IZQUIERDA);
					else if (rnd < 0.50)
						j.getFelix().mover(Orientacion.DERECHA);
					else if (rnd < 0.75)
						j.getFelix().mover(Orientacion.ABAJO);
					else
						j.getFelix().mover(Orientacion.ARRIBA);
					System.out.println("Felix se ha movido a " + j.getFelix().getPosicion());
					for (int i = 0; i < 4; i++) {
						j.darMartillazo();
					}
					*/
					timer.resetear();
					lista = j.getMapa().getComponentesDibujables();
					lista.add(j.getRalph());
					lista.add(j.getFelix());
					Graficador.refrescarTopDown(lista);
				}
				j.hacerTodo();
			} else {
				Graficador.refrescarTopDown(new ArrayList<Dibujable>());
				//break;
			}
		}
	}

}
