package juego;

import utils.Posicion;
import ventanas.VentanaPrimerPiso;
import ventanas.VentanaPuerta;

/**
 * Representa a la primera sección, que se diferencia por
 * tener dos ventanas circulares (la puerta y la del primer piso)
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class PrimeraSeccion extends Seccion {

	public PrimeraSeccion(int ventanasRotas, int ventanasConObs) {
		super(ventanasRotas, ventanasConObs, 1);
		Posicion posPuerta = ventanas[2][2].getPosicion();
		posPuerta = new Posicion(posPuerta.getX()-7, posPuerta.getY()-5);
		Posicion posPrimerPiso = ventanas[1][2].getPosicion();
		posPrimerPiso = new Posicion(posPrimerPiso.getX()-7, posPrimerPiso.getY());
		
		ventanas[2][2] = new VentanaPuerta(posPuerta, ventanas[2][2].getSeccion(),
				ventanas[2][2].estaRota());
		ventanas[1][2] = new VentanaPrimerPiso(posPrimerPiso, ventanas[1][2].getSeccion(),
				ventanas[1][2].estaRota());
		posicion = new Posicion(0,0);
		for (int i=0;i<2;i++) {
			for (int j=0;j<5;j++) {
				ventanas[i][j].getPosicion().moverY(10);
			}
		}
	}

	public PrimeraSeccion(Seccion s) {
		super(s.ventanasRotas, s.ventanasConObstaculo, 1);
	}
}
