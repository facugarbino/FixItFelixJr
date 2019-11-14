package vistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.JuegoMain;
import juego.Juego;
import utils.ColorDeLetra;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PanelInfo extends JPanel {

	private Juego juego;
	private int vidas;
	private int nroNivel;
	private long puntaje;
	private int tiempo;
	private Image cabezaFelix;
	private JPanel contentPane;
	private JLabel botonPausa = new JLabel();
	private JLabel botonAudio = new JLabel();
	private static double MULTIPLICADOR =  JuegoMain.MULTIPLICADOR;
	
	
	private void setBounds2(JComponent comp, int x1, int y1, int x2, int y2) {
		comp.setBounds((int)(x1*MULTIPLICADOR), (int)(y1*MULTIPLICADOR),
				(int)(x2*MULTIPLICADOR), (int)(y2*MULTIPLICADOR));
	}

	public PanelInfo() {
		setBounds(0, 0, (int) (420 * JuegoMain.MULTIPLICADOR), (int) (50 * JuegoMain.MULTIPLICADOR));
		setLayout(null);
		setBackground(Color.BLACK);
//		JLabel label = new JLabel();
//		label.setBounds(12, 12, 98, 20);
//		label.setIcon(new ImagenTextual("Puntaje: 23", 1.5, ColorDeLetra.ROJO).getImageIcon());
		// add(label);
		
		botonPausa.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/botones/botonPausa.png")));
		botonPausa.setBounds((int)(395*JuegoMain.MULTIPLICADOR),(int)(20*JuegoMain.MULTIPLICADOR),28,28);
		botonPausa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Juego.getInstance().pausar();
				Juego.getInstance().graficarPausar();
			}
		});
		add(botonPausa);
		
		
		
		juego = Juego.getInstance();
		try {
			cabezaFelix = ImageIO.read(getClass().getResource("/recursos/imagenes/extra/cabezaFelix.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		actualizar();
		int tamañoLetras = 2;
		g.drawImage(new ImagenTextual("Nivel: " + nroNivel, tamañoLetras, ColorDeLetra.ROJO).getBufferedImage(),
				(int) (10 * JuegoMain.MULTIPLICADOR), (int) (10 * JuegoMain.MULTIPLICADOR), null);
		g.drawImage(new ImagenTextual("Puntaje: " + puntaje, tamañoLetras, ColorDeLetra.ROJO).getBufferedImage(),
				(int) (10 * JuegoMain.MULTIPLICADOR), (int) (30 * JuegoMain.MULTIPLICADOR), null);
		BufferedImage textVidas = new ImagenTextual("Vidas: ", tamañoLetras , ColorDeLetra.ROJO).getBufferedImage();
		g.drawImage(textVidas, (int) (250 * JuegoMain.MULTIPLICADOR), (int) (10 * JuegoMain.MULTIPLICADOR), null);
		for (int i = 0; i < juego.getFelix().getVidas(); i++) {
			g.drawImage(cabezaFelix, (int) (250 * JuegoMain.MULTIPLICADOR) + (int) (textVidas.getWidth())+
					i*2*(int)(cabezaFelix.getWidth(null)*JuegoMain.MULTIPLICADOR),
					(int) (10 * JuegoMain.MULTIPLICADOR) ,
					(int) (cabezaFelix.getWidth(null) * JuegoMain.MULTIPLICADOR),
					(int) (cabezaFelix.getHeight(null) * JuegoMain.MULTIPLICADOR), null);
		}

		g.drawImage(new ImagenTextual("Tiempo: " + tiempo, tamañoLetras , ColorDeLetra.ROJO).getBufferedImage(),
				(int) (250 * JuegoMain.MULTIPLICADOR), (int) (30 * JuegoMain.MULTIPLICADOR), null);
	}

	private void actualizar() {
		nroNivel = juego.getNroNivel();
		tiempo = juego.getTiempo();
		puntaje = juego.getFelix().getPuntaje() + juego.getJugador().getPuntaje();
		vidas = juego.getFelix().getVidas();
	}
}
