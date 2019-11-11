package vistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import controlador.JuegoMain;
import utils.ColorDeLetra;

public class ImagenTextual {
	private BufferedImage imagen;
	private final static double MULTIPLICADOR = JuegoMain.MULTIPLICADOR_MENU;
	

	public ImagenTextual(String texto, double multiplicador, ColorDeLetra color) {
		multiplicador*=MULTIPLICADOR;
		String textoColor = color.toString().toLowerCase();
		imagen = new BufferedImage((int) (8 * texto.length() * multiplicador), (int) (9 * multiplicador),
				BufferedImage.TYPE_INT_RGB);
		Graphics g = imagen.getGraphics();
		try {
			texto = texto.toLowerCase();
			texto = texto.replace(' ','_');
			for (int i = 0; i < texto.length(); i++) {
				Image imagen = ImageIO.read(
						getClass().getResource("/recursos/imagenes/letras/" + textoColor + "/" + texto.charAt(i) + ".png"));
				g.drawImage(
						imagen.getScaledInstance((int) (imagen.getWidth(null) * multiplicador),
								(int) (imagen.getHeight(null) * multiplicador), Image.SCALE_DEFAULT),
						(int) (i * 8 * multiplicador), 0, null);
			}
		} catch (IOException e) {
			System.out.println("Entre al catch");
			e.printStackTrace();
		}
	}

	public BufferedImage getBufferedImage() {
		return imagen;
	}

	public ImageIcon getImageIcon() {
		return new ImageIcon(imagen);
	}
}
