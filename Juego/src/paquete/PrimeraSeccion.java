package paquete;

import ventanas.VentanaPrimerPiso;
import ventanas.VentanaPuerta;

public class PrimeraSeccion extends Seccion {

	public PrimeraSeccion(int ventanasRotas, int ventanasConObs) {
		super(ventanasRotas, ventanasConObs, 1);
		ventanas[2][2] = new VentanaPuerta(ventanas[2][2].getPosicion(), ventanas[2][2].getSeccion(),
				ventanas[2][2].estaRota());
		ventanas[2][1] = new VentanaPrimerPiso(ventanas[2][1].getPosicion(), ventanas[2][1].getSeccion(),
				ventanas[2][1].estaRota());
	}

	public PrimeraSeccion(Seccion s) {
		super(s.ventanasRotas, s.ventanasConObstaculo, 1);
	}
}
