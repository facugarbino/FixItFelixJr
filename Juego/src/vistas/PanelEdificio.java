package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import componentes.Componente;
import componentes.Nube;
import componentes.Pajaro;
import controlador.JuegoMain;
import juego.Edificio;
import juego.Juego;
import juego.Mapa;
import juego.Seccion;
import personajes.FelixJr;
import personajes.Ralph;
import utils.Orientacion;
import ventanas.*;
import ventanas.extra.Nicelander;
import ventanas.extra.Pastel;
import ventanas.obstaculos.Obstaculo;
import ventanas.paneles.Panel;

@SuppressWarnings("serial")
public class PanelEdificio extends JPanel {

	private final int ANCHO = 420;
	private final int ALTO = 650;
//	private final int ANCHO = 300;
//	private final int ALTO = 900;
	private Image[] imagenes;
	private Edificio edificio;
	private Mapa mapa;

	public PanelEdificio() {
		setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension((int)(ANCHO*JuegoMain.MULTIPLICADOR), (int)(ALTO*JuegoMain.MULTIPLICADOR)));
		this.setVisible(true);
		try {
			imagenes = getImagenes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		edificio = Juego.getInstance().getMapa().getEdificio();
		mapa = Juego.getInstance().getMapa();
	}

	private void dibujarImagen(Graphics g, Image imagen, int x, int y) {
		g.drawImage(imagen, (int)(x*JuegoMain.MULTIPLICADOR),(int)(y*JuegoMain.MULTIPLICADOR),
				(int)(imagen.getWidth(null)*JuegoMain.MULTIPLICADOR),
				(int)(imagen.getHeight(null)*JuegoMain.MULTIPLICADOR), null);
		
	}
	private Image[] getImagenes() throws IOException {
		Image img[] = new Image[42];
		String url;
		String[] urls = new String[] { "edificio/edificio", "ventanas/ventanaComun", "ventanas/ventanaPrimerPiso",
				"ventanas/ventanaConHojas", "puertas/puerta", "obstaculos/macetero", "obstaculos/moldura",
				"obstaculos/hojaIzquierda", "obstaculos/hojaDerecha", "nicelanders/nicelanderCompleto1",
				"nicelanders/nicelanderCompleto2", "nicelanders/nicelanderCompleto3", "nicelanders/nicelanderOculto1",
				"nicelanders/nicelanderOculto2", "nicelanders/nicelanderOculto3", "pastel/pastel1", "pastel/pastel2",
				"paneles/panelRoto", "paneles/panelSano", "paneles/panelMedioRoto1", "paneles/panelMedioRoto2",

				"nubes/nube", "pajaros/pajaroDer1", "pajaros/pajaroDer2", "pajaros/pajaroIzq1", "pajaros/pajaroIzq2",
				"felix/felixConPastel", "felix/felixConMartillo1", "felix/felixConMartillo2", "felix/felixCorre",
				"felix/felixFrente", "felix/felixGolpeado", "ralph/ralphCaminaIzq1", "ralph/ralphCaminaIzq2",
				"ralph/ralphCaminaDer1", "ralph/ralphCaminaDer2", "ralph/ralphTiraLadrillo1",
				"ralph/ralphTiraLadrillo2", "ralph/ralphDeFrente", "ralph/ralphSube1", "ralph/ralphSube2",
				"ladrillos/ladrillo" };
		for (int i = 0; i < 42; i++) {
			url = urls[i];
			img[i] = ImageIO.read(getClass().getResource("/recursos/imagenes/" + urls[i] + ".png"));
		}
		return img;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		mapa = Juego.getInstance().getMapa();
		edificio = mapa.getEdificio();
		dibujarNubes(g);
		dibujarEdificio(g);
		dibujarVentanas(g);
		dibujarComponentes(g);
		dibujarPastelesYNicelanders(g);
		dibujarPersonajes(g);
	}

	private void dibujarPastelesYNicelanders(Graphics g) {
		Nicelander nicelander;
		Image imagen;
		int numImagen = 0;
		if ((nicelander = edificio.getSeccionActual().getNicelander()) != null) {
			if (nicelander.estaOculto()) {
				switch (nicelander.getTipo()) {
				case 1:
					numImagen = 12;
					break;
				case 2:
					numImagen = 13;
					break;
				case 3:
					numImagen = 14;
					break;
				}
			} else {
				switch (nicelander.getTipo()) {
				case 1:
					numImagen = 9;
					break;
				case 2:
					numImagen = 10;
					break;
				case 3:
					numImagen = 11;
					break;
				}
			}
			imagen = imagenes[numImagen];
//			g.drawImage(imagen, nicelander.getPosicion().getX(),
//					ALTO - nicelander.getPosicion().getY() - imagen.getHeight(null), null);
			dibujarImagen(g,imagen, nicelander.getPosicion().getX(),
					ALTO - nicelander.getPosicion().getY() - imagen.getHeight(null));
			
		}
		List<Pastel> pasteles = edificio.getSeccionActual().getPasteles();
		for (Pastel p : pasteles) {
			if (p.getEstado()) {
				numImagen = 15;
			} else {
				numImagen = 16;
			}

			imagen = imagenes[numImagen];
//			g.drawImage(imagen, p.getPosicion().getX(), 
//					ALTO - p.getPosicion().getY() - imagen.getHeight(null), null);
			dibujarImagen(g,imagen,p.getPosicion().getX(), 
					ALTO - p.getPosicion().getY() - imagen.getHeight(null));
		}
	}

	private void dibujarNubes(Graphics g) {
		List<Componente> componentes = mapa.getComponentes();
		for (Componente c : componentes) {
			if (c instanceof Nube) {
//				g.drawImage(imagenes[21], c.getPosicion().getX(),
//						ALTO - c.getPosicion().getY() - imagenes[21].getHeight(null), null);
				dibujarImagen(g,imagenes[21], c.getPosicion().getX(),
						ALTO - c.getPosicion().getY() - imagenes[21].getHeight(null));
				
			}
		}

	}

	private void dibujarVentanas(Graphics g) {
		Image imagen;
		String clase;
		for (int i = 1; i < 4; i++) {
			Seccion s = edificio.getSeccion(i);
			List<Ventana> lista = s.getVentanas();
			for (Ventana v : lista) {
				dibujarPaneles(g, v);
				clase = claseDe(v);
				int numImagen = 1;
				switch (clase) {
				case "VentanaComun": {
					numImagen = 1;
					break;
				}
				case "VentanaConHojas": {
					VentanaConHojas ven = (VentanaConHojas) v;
					if (ven.estaCerrada()) {
						numImagen = 3;
					} else {
						numImagen = 1;
					}
					break;
				}
				case "VentanaPrimerPiso": {
					numImagen = 2;
					break;
				}
				case "VentanaPuerta": {
					numImagen = 4;
					break;
				}
				}
				imagen = imagenes[numImagen];
				
//				g.drawImage(imagen, v.getPosicion().getX(), ALTO - v.getPosicion().getY() - imagen.getHeight(null),
//						null);
				
				dibujarImagen(g,imagen, v.getPosicion().getX(), ALTO - v.getPosicion().getY() - imagen.getHeight(null));
				
				dibujarObstaculos(g, v);
			}
		}
	}

	private String claseDe(Object o) {
		String clase = o.getClass().toString();
		clase = clase.substring(clase.indexOf('.') + 1, clase.length());
		clase = clase.substring(clase.indexOf('.') + 1, clase.length());
		return clase;
	}

	private void dibujarObstaculos(Graphics g, Ventana v) {
		Image imagen;
		int numImagen = 1;
		String clase;
		for (Obstaculo o : v.getObstaculos()) {
			clase = claseDe(o);
			switch (clase) {
			case "Moldura": {
				numImagen = 6;
				break;
			}
			case "Macetero": {
				numImagen = 5;
				break;
			}
			case "HojaIzquierda": {
				numImagen = 7;
				break;
			}
			case "HojaDerecha": {
				numImagen = 8;
				break;
			}
			}
			imagen = imagenes[numImagen];
//			g.drawImage(imagenes[numImagen], v.getPosicion().getX() + o.getPosicion().getX(),
//					ALTO - (v.getPosicion().getY() + o.getPosicion().getY()) - imagen.getHeight(null), null);
			
			dibujarImagen(g, imagenes[numImagen], v.getPosicion().getX() + o.getPosicion().getX(),
					ALTO - (v.getPosicion().getY() + o.getPosicion().getY()) - imagen.getHeight(null));
		}
	}

	private int dibujarPaneles(Graphics g, Ventana v) {
		Image imagen;
		int numImagen = 18;
		String clase;
		List<Panel> paneles = v.getPaneles();
		for (Panel p : paneles) {
			clase = claseDe(p.getEstado());
			switch (clase) {
			case "Sano": {
				numImagen = 18;
				break;
			}
			case "MedioRoto": {
				numImagen = 19;
				break;
			}
			case "Roto": {
				numImagen = 17;
				break;
			}
			}
			imagen = imagenes[numImagen];
//			g.drawImage(imagen, v.getPosicion().getX() + p.getPosicion().getX(),
//					ALTO - (v.getPosicion().getY() + p.getPosicion().getY()) - imagen.getHeight(null), null);
			
			
			dibujarImagen(g,imagen, v.getPosicion().getX() + p.getPosicion().getX(),
					ALTO - (v.getPosicion().getY() + p.getPosicion().getY()) - imagen.getHeight(null));
			
		}
		return numImagen;
	}

	private void dibujarEdificio(Graphics g) {
//		g.drawImage(imagenes[0], edificio.getPosicion().getX(), edificio.getPosicion().getY(), null);
		dibujarImagen(g, imagenes[0], edificio.getPosicion().getX(), edificio.getPosicion().getY());
	}

	private void dibujarPersonajes(Graphics g) {
		Image imagen;
		int numImagen = 0;
		FelixJr felix = Juego.getInstance().getFelix();
		if (felix.estaSaltando()) {
			numImagen = 8;
		} else {
			numImagen = 9;
		}
		imagen = imagenes[numImagen + 21];
//		g.drawImage(imagen, felix.getPosicion().getX(), ALTO - felix.getPosicion().getY() - imagen.getHeight(null),
//				null);
		dibujarImagen(g,imagen, felix.getPosicion().getX(), ALTO - felix.getPosicion().getY() - imagen.getHeight(null));

		Ralph ralph = Juego.getInstance().getRalph();
		switch (ralph.getOrientacion()) {
		case DERECHA: {
			if (ralph.getPosicionDeTiro()) {
				numImagen = 13;
			} else {
				numImagen = 14;
			}
			break;
		}
		case IZQUIERDA: {
			if (ralph.getPosicionDeTiro()) {
				numImagen = 11;
			} else {
				numImagen = 12;
			}
			break;
		}
		default: {
			if (ralph.estaTirandoLadrillos()) {
				if (ralph.getPosicionDeTiro()) {
					numImagen = 15;
				} else {
					numImagen = 16;
				}
			} else {
				numImagen = 17;
			}
			break;
		}
		}
		imagen = imagenes[numImagen + 21];
//		g.drawImage(imagen, ralph.getPosicion().getX(), ALTO - ralph.getPosicion().getY() - imagen.getHeight(null),
//				null);
		
		dibujarImagen(g,imagen, ralph.getPosicion().getX(), ALTO - ralph.getPosicion().getY() - imagen.getHeight(null));
	}

	private void dibujarComponentes(Graphics g) {
		Image imagen;
		List<Componente> componentes = mapa.getComponentes();
		for (Componente c : componentes) {
			int numImagen = 0;
			String clase = claseDe(c);
			switch (clase) {
			case "Ladrillo": {
				numImagen = 20;
				break;
			}
			case "Pajaro": {
				Pajaro p = (Pajaro) c;
				if (p.getOrientacion() == Orientacion.DERECHA) {
					if (p.getAleteo()) {
						numImagen = 1;
					} else {
						numImagen = 2;
					}
				} else {
					if (p.getAleteo()) {
						numImagen = 3;
					} else {
						numImagen = 4;
					}
				}
				break;
			}
			default:
				continue;
			}
			imagen = imagenes[numImagen + 21];
//			g.drawImage(imagen, c.getPosicion().getX()- imagen.getWidth(null)/2, 
//					ALTO - c.getPosicion().getY() - imagen.getHeight(null)/2, null);
			
			dibujarImagen(g,imagen, c.getPosicion().getX()- imagen.getWidth(null)/2, 
					ALTO - c.getPosicion().getY() - imagen.getHeight(null)/2);
		}
	}

}