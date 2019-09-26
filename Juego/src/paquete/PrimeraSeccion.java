package paquete;

public class PrimeraSeccion extends Seccion {

	public PrimeraSeccion(int ventanasRotas, int ventanasConObs) {
		super(ventanasRotas, ventanasConObs);
		ventanas[2][2] = new VentanaPuerta(ventanas[2][2].getPosicion(), ventanas[2][2].getSeccion(),
				ventanas[2][2].estaRota());
		ventanas[2][1] = new VentanaPrimerPiso(ventanas[2][1].getPosicion(), ventanas[2][1].getSeccion(),
				ventanas[2][1].estaRota());
	}

	public PrimeraSeccion(PrimeraSeccion s) {
		super(s.ventanasRotas, s.ventanasConObstaculo);
		ventanas[2][2] = new VentanaPuerta(ventanas[2][2].getPosicion(), ventanas[2][2].getSeccion(),
				ventanas[2][2].estaRota());
		ventanas[2][1] = new VentanaPrimerPiso(ventanas[2][1].getPosicion(), ventanas[2][1].getSeccion(),
				ventanas[2][1].estaRota());
	}
}
