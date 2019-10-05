package paquete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import taller2.modelo.Dibujable;
import taller2.test.Dato;
import taller2.vista.Graficador;

public class TestJuego {

	static Juego j;

	public static void main(String[] args) {
		Juego.crearJuego("Facundo");
		j = Juego.getJuego();
		loop();
	}

	private static List<Dibujable> unirListas(List<List<Dibujable>> lista) {
		List<Dibujable> listaR = new ArrayList<>();
		Iterator<List<Dibujable>> iterator;
		iterator = lista.iterator();
		while (iterator.hasNext()) {
			Iterator<Dibujable> iterator2 = iterator.next().iterator();
			while (iterator2.hasNext()) {
				listaR.add(iterator2.next());
			}
		}
		return listaR;
	}

	static void loop() {
		Contador timer = new Contador(500);
		System.out.println("Felix comienza en la posición " + j.getFelix().getPosicion());
		List<Dibujable> lista = new ArrayList<Dibujable>(); 
		while (true) {
			if (!j.estaPausado()) {
				if (timer.contar()) {
					/*
					double xd = Math.random();
					if (xd < 0.25)
						j.getFelix().mover(Orientacion.IZQUIERDA);
					else if (xd < 0.50)
						j.getFelix().mover(Orientacion.DERECHA);
					else if (xd < 0.75)
						j.getFelix().mover(Orientacion.ABAJO);
					else
						j.getFelix().mover(Orientacion.ARRIBA);
					timer.resetear();
					for (int i = 0; i < 4; i++) {
						j.darMartillazo();
						System.out.println("Felix ha dado un martillazo!");
					}
					System.out.println("Felix se ha movido a " + j.getFelix().getPosicion());
					*/
					List<List<Dibujable>> listaGeneral = new ArrayList<>();
					listaGeneral.add(j.getMapa().getComponentesDibujables());
					listaGeneral.add(j.getMapa().getEdificio().getSeccion(1).getComponentesDibujables());
					listaGeneral.add(j.getMapa().getEdificio().getSeccion(2).getComponentesDibujables());
					listaGeneral.add(j.getMapa().getEdificio().getSeccion(3).getComponentesDibujables());
					lista = unirListas(listaGeneral);
					lista.add(new Dato(j.getRalph().getPosicion().getX(),300-j.getRalph().getPosicion().getY(),'R'));
					lista.add(new Dato(j.getFelix().getPosicion().getX(),300-j.getFelix().getPosicion().getY(),'F'));
					
					Graficador.refrescarTopDown(lista,1);
					
				}
				j.hacerTodo();
				
			} else {
				Graficador.refrescarTopDown(lista,1);
				break;
			}
		}
	}

}
