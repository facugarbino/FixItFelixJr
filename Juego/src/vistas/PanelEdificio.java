package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import juego.Edificio;
import juego.Juego;
import juego.Seccion;
import ventanas.Ventana;

@SuppressWarnings("serial")
public class PanelEdificio extends JPanel {

	private final int ANCHO = 210;
	private final int ALTO = 650;
	private Image[] imagenes;
	private Edificio edificio;

	public PanelEdificio() {
		setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(ANCHO, ALTO));
		this.setVisible(true);
		try {
			imagenes = getImagenes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		edificio = Juego.getInstance().getMapa().getEdificio();
	}

	private Image[] getImagenes() throws IOException {
		Image img[] = new Image[27];
		String[] urls = new String[] { "edificio/edificio", "puertas/puertaCon2PanelesRotos",
				"puertas/puertaCon3PanelesRotos", "puertas/puertaSana", "obstaculos/macetero", "obstaculos/moldura",
				"obstaculos/hojaIzquierda", "obstaculos/hojaDerecha", "ventanas/ventanaComun1",
				"ventanas/ventanaComun2", "ventanas/ventanaComun3", "ventanas/ventanaComun4", "ventanas/ventanaComun5",
				"ventanas/ventanaComun6", "ventanas/ventanaComun7", "ventanas/ventanaComun8", "ventanas/ventanaComun9",
				"ventanas/ventanaPuerta", "ventanas/ventanaPuertaRota", "nicelander/nicelanderCompleto1",
				"nicelander/nicelanderCompleto2", "nicelander/nicelanderCompleto3", "nicelander/nicelanderOculto1",
				"nicelander/nicelanderOculto2", "nicelander/nicelanderOculto3", "nicelander/nicelander4",
				"pastel/pastel1", "pastel/pastel2" };
		for (int i = 0; i < 1; i++) {
			img[i] = ImageIO.read(getClass().getResource("/recursos/imagenes/" + urls[i] + ".png"));
		}
		return img;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		dibujarEdificio(g);
		dibujarVentanas(g);
	}

	private void dibujarVentanas(Graphics g) {
		for (int i = 0; i < 3; i++) {
			Seccion s = edificio.getSeccion(i);
			List<Ventana> lista = s.getVentanas();
			for (Ventana v : lista) {
				g.drawImage(imagenes[1], v.getPosicion().getX(), ALTO - v.getPosicion().getY(), null);
			}

		}

	}

	private void dibujarEdificio(Graphics g) {
		g.drawImage(imagenes[0], 0, 0, null);
	}
}
