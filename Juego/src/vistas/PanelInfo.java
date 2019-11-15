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

import controlador.Audio;
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
	private JLabel botonPausa = new JLabel();
	private JLabel botonAudio = new JLabel();
	private static double MULTIPLICADOR = JuegoMain.MULTIPLICADOR;
	private static ImageIcon imgBotonPausa;
	private static ImageIcon imgBotonMutear;
	private static ImageIcon imgBotonDesmutear;

	static {
		imgBotonPausa = new ImageIcon(PanelInfo.class.getResource("/recursos/imagenes/botones/botonPausa.png"));
		imgBotonMutear = new ImageIcon(PanelInfo.class.getResource("/recursos/imagenes/botones/botonMutear.png"));
		imgBotonDesmutear = new ImageIcon(PanelInfo.class.getResource("/recursos/imagenes/botones/botonDesmutear.png"));
	}

	public PanelInfo() {
		setBounds(0, 0, (int) (420 * MULTIPLICADOR), (int) (50 * MULTIPLICADOR));
		setLayout(null);
		setBackground(Color.BLACK);

		botonPausa.setIcon(imgBotonPausa);
		botonPausa.setBounds((int) (395 * MULTIPLICADOR), (int) (25 * MULTIPLICADOR), 28, 28);
		botonPausa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Juego.getInstance().pausar();
				Juego.getInstance().graficarPausar();
			}
		});
		add(botonPausa);

		botonAudio.setIcon(imgBotonDesmutear);
		botonAudio.setBounds((int) (395 * MULTIPLICADOR), (int) (10 * MULTIPLICADOR), 28, 28);
		botonAudio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Audio.getInstance().setActivado();
				Audio.getInstance().fondo(Audio.getInstance().getActivado());
				if (Audio.getInstance().getActivado()) {
					botonAudio.setIcon(imgBotonDesmutear);
				} else {
					botonAudio.setIcon(imgBotonMutear);
				}
			}
		});
		add(botonAudio);
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
				(int) (10 * MULTIPLICADOR), (int) (10 * MULTIPLICADOR), null);
		g.drawImage(new ImagenTextual("Puntaje: " + puntaje, tamañoLetras, ColorDeLetra.ROJO).getBufferedImage(),
				(int) (10 * MULTIPLICADOR), (int) (30 * MULTIPLICADOR), null);
		BufferedImage textVidas = new ImagenTextual("Vidas: ", tamañoLetras, ColorDeLetra.ROJO).getBufferedImage();
		g.drawImage(textVidas, (int) (250 * MULTIPLICADOR), (int) (10 * MULTIPLICADOR), null);
		for (int i = 0; i < juego.getFelix().getVidas(); i++) {
			g.drawImage(cabezaFelix,
					(int) (250 * MULTIPLICADOR) + (int) (textVidas.getWidth())
							+ i * 2 * (int) (cabezaFelix.getWidth(null) * MULTIPLICADOR),
					(int) (10 * MULTIPLICADOR), (int) (cabezaFelix.getWidth(null) * MULTIPLICADOR),
					(int) (cabezaFelix.getHeight(null) * MULTIPLICADOR), null);
		}
		g.drawImage(new ImagenTextual("Tiempo: " + tiempo, tamañoLetras, ColorDeLetra.ROJO).getBufferedImage(),
				(int) (250 * MULTIPLICADOR), (int) (30 * MULTIPLICADOR), null);
	}

	private void actualizar() {
		nroNivel = juego.getNroNivel();
		tiempo = juego.getTiempo();
		puntaje = juego.getFelix().getPuntaje() + juego.getJugador().getPuntaje();
		vidas = juego.getFelix().getVidas();
	}
}
