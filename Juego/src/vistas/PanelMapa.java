package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import componentes.Componente;
import componentes.Nube;
import componentes.Pajaro;
import juego.Juego;
import juego.Mapa;
import personajes.FelixJr;
import personajes.Ralph;
import utils.Orientacion;

public class PanelMapa extends JPanel {

	private Image[] imagenes;
	private PanelEdificio panelEdificio;
	private Mapa mapa;
	private final int ANCHO = 420;
	private final int ALTO = 650;

	public PanelMapa() {
		try {
			imagenes = getImagenes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		panelEdificio = new PanelEdificio();
		add(panelEdificio);
		setBackground(Color.BLACK);
		setBounds(0, 0, ANCHO, ALTO);
		this.setVisible(true);
		
		mapa = Juego.getInstance().getMapa();
	}

	public Image[] getImagenesVector() {
		return imagenes;
	}
	private Image[] getImagenes() throws IOException {
		Image img[] = new Image[21];
		String[] urls = new String[] { "nubes/nube", "pajaros/pajaroDer1", "pajaros/pajaroDer2", "pajaros/pajaroIzq1",
				"pajaros/pajaroIzq2", "felix/felixConPastel", "felix/felixConMartillo1", "felix/felixConMartillo2",
				"felix/felixCorre", "felix/felixFrente", "felix/felixGolpeado", "ralph/ralphCaminaIzq1",
				"ralph/ralphCaminaIzq2", "ralph/ralphCaminaDer1", "ralph/ralphCaminaDer2",
				"ralph/ralphTiraLadrillo1", "ralph/ralphTiraLadrillo2", 
				"ralph/ralphDeFrente","ralph/ralphSube1", "ralph/ralphSube2",
				"ladrillos/ladrillo" };
		for (int i = 0; i < 1; i++) {
			img[i] = ImageIO.read(getClass().getResource("/recursos/imagenes/" + urls[i] + ".png"));
		}
		return img;
	}

	public void paintComponent(Graphics g) {
		//panelEdificio.pintar();
		super.paintComponent(g);
//		List<Componente> componentes = mapa.getComponentes();
//		dibujarComponentes(g, componentes);
//		dibujarPersonajes(g);
	}

	private void dibujarPersonajes(Graphics g) {
		int numImagen = 0;
		FelixJr felix = Juego.getInstance().getFelix();
		if (felix.estaSaltando()) {
			numImagen = 8;
		} else {
			numImagen = 9;
		}
		g.drawImage(imagenes[numImagen], felix.getPosicion().getX(), felix.getPosicion().getY(), null);

		Ralph ralph = Juego.getInstance().getRalph();
		switch (ralph.getOrientacion()) {
		case DERECHA: {
			if (ralph.getSwap()) {
				numImagen=13;
			} else {
				numImagen=14;
			}
			break;
		}
		case IZQUIERDA: {
			if (ralph.getSwap()) {
				numImagen=11;
			} else {
				numImagen=12;
			}
			break;
		}
		default: {
			if (ralph.estaTirandoLadrillos()) {
				if (ralph.getSwap()) {
					numImagen=15;
				} else {
					numImagen=16;
				}
			} else {
				numImagen=17;
			}
			break;
		}
		}
		g.drawImage(imagenes[numImagen], ralph.getPosicion().getX(),ALTO - ralph.getPosicion().getY(), null);
	}

	private void dibujarComponentes(Graphics g, List<Componente> componentes) {
		for (Componente c : componentes) {
			int numImagen = 0;
			String clase = claseDe(c);
			switch (clase) {
			case "Nube": {
				numImagen = 0;
				break;
			}
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

			}
			g.drawImage(imagenes[numImagen], c.getPosicion().getX(), ALTO - c.getPosicion().getY(), null);
		}
	}

	private String claseDe(Object o) {
		String clase = o.getClass().toString();
		clase = clase.substring(clase.indexOf('.') + 1, clase.length());
		clase = clase.substring(clase.indexOf('.') + 1, clase.length());
		return clase;
	}

}
