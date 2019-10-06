package juego;

import ventanas.VentanaPrimerPiso;
import ventanas.VentanaPuerta;

public class PrimeraSeccion extends Seccion {

	public PrimeraSeccion(int ventanasRotas, int ventanasConObs) {
		super(ventanasRotas, ventanasConObs, 1);
		ventanas[2][2] = new VentanaPuerta(ventanas[2][2].getPosicion(), ventanas[2][2].getSeccion(),
				ventanas[2][2].estaRota());
		ventanas[1][2] = new VentanaPrimerPiso(ventanas[1][2].getPosicion(), ventanas[1][2].getSeccion(),
				ventanas[1][2].estaRota());
	}

	public PrimeraSeccion(Seccion s) {
		super(s.ventanasRotas, s.ventanasConObstaculo, 1);
	}
}
