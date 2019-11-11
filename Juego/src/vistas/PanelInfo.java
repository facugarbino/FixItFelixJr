package vistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import controlador.JuegoMain;
import juego.Juego;
import utils.ColorDeLetra;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PanelInfo extends JPanel {

	private Juego juego;
	private int vidas;
	private int nroNivel;
	private long puntaje;
	private int tiempo;

	public PanelInfo() {
		setBounds(0, 0, (int)(420*JuegoMain.MULTIPLICADOR), (int)(50*JuegoMain.MULTIPLICADOR));
		setLayout(null);
		setBackground(Color.BLACK);
//		JLabel label = new JLabel();
//		label.setBounds(12, 12, 98, 20);
//		label.setIcon(new ImagenTextual("Puntaje: 23", 1.5, ColorDeLetra.ROJO).getImageIcon());
		//add(label);

		juego = Juego.getInstance();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		actualizar();
		g.drawImage(new ImagenTextual("Nivel: "+nroNivel, 1.5, ColorDeLetra.ROJO).getBufferedImage(), 10, 10, null);
		g.drawImage(new ImagenTextual("Puntaje: "+puntaje, 1.5, ColorDeLetra.ROJO).getBufferedImage(), 10, 30, null);
		g.drawImage(new ImagenTextual("Vidas: "+vidas, 1.5, ColorDeLetra.ROJO).getBufferedImage(), 250, 10, null);
		g.drawImage(new ImagenTextual("Tiempo: "+tiempo, 1.5, ColorDeLetra.ROJO).getBufferedImage(), 250, 30, null);
	}
	
	private void actualizar() {
		nroNivel = juego.getNroNivel();
		tiempo = juego.getTiempo();
		puntaje = juego.getFelix().getPuntaje() + juego.getJugador().getPuntaje();
		vidas = juego.getFelix().getVidas();
	}
}
