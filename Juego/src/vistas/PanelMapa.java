package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelMapa extends JPanel {


	private Image[] imagenes;
	private PanelEdificio panelEdificio;
	
	public PanelMapa() {
		panelEdificio = new PanelEdificio();
		add(panelEdificio);
		setBackground(Color.WHITE);
		setBounds(0,0,420,650);
		this.setVisible(true);
		try {
			imagenes = getImagenes();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Image[] getImagenes() throws IOException {
		Image img[] = new Image[21];
		String[] urls = new String[]{
				"nubes/nube", "pajaros/pajaroDer1", "pajaros/pajaroDer2",
				"pajaros/pajaroIzq1", "pajaros/pajaroIzq2", 
				"felix/felixConPastel","felix/felixConMartillo1", "felix/felixConMartillo2",
				"felix/felixCorre","felix/felixFrente", "felix/felixGolpeado",
				"ralph/ralphCaminaIzq1", "ralph/ralphCaminaIzq2","ralph/ralphCaminaDer1", 
				"ralph/ralphCaminaDer2", "ralph/ralphDeFrente", "ralph/ralphTiraLadrillo1",
				"ralph/ralphTiraLadrillo2", "ralph/ralphSube1", "ralph/ralphSube2",
				"ladrillos/ladrillo"
		};
		for (int i=0;i<0;i++) {
			img[i] = ImageIO.read(getClass().getResource("/recursos/imagenes/"+urls[i]+".png"));
		}
		return img;
	}
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//	}
	
}
