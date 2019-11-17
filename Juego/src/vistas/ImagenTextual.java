package vistas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import controlador.JuegoMain;
import utils.ColorDeLetra;

/**
 * Representa a una imagen con un texto definido con la fuente
 * de Fix it Felix Jr.
 * 
 * @author Garbino y Rodriguez Murphy
 *
 */
public class ImagenTextual {
	private BufferedImage imagen;
	private final static double MULTIPLICADOR = JuegoMain.MULTIPLICADOR_MENU;
	private static Map<ColorDeLetra,Map<Character, Image>> imagenes;
	
	static{
		List<Character> caracteres = Arrays.asList(new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', '_', '-', ',', '!', '?',
				'"', '(', ')', '\'', '[', ']', '@', '*', '&', '%', '$', '#', '>', '<', '=', '1', '2', '3',
				'4', '5', '6', '7', '8', '9', '0',':'});
		imagenes =  new HashMap<>();
		for (ColorDeLetra c : ColorDeLetra.values()) {
			Map<Character,Image> map = new HashMap<>();
			for (Character car: caracteres) {
				Image imagen;
				try {
					imagen = ImageIO.read(
							ImagenTextual.class.getResource("/recursos/imagenes/letras/" +
					c.toString().toLowerCase() + "/" + car + ".png"));
					map.put(car, imagen);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			imagenes.put(c, map);
		}
	}

	public ImagenTextual(String texto, double multiplicador, ColorDeLetra color) {
		multiplicador*=MULTIPLICADOR;
		imagen = new BufferedImage((int) (8 * texto.length() * multiplicador), (int) (9 * multiplicador),
				BufferedImage.TYPE_INT_RGB);
		Graphics g = imagen.getGraphics();
		texto = texto.toLowerCase();
		texto = texto.replace(' ','_');
		for (int i = 0; i < texto.length(); i++) {
			Image imagen = imagenes.get(color).get(texto.charAt(i));
			g.drawImage(
					imagen.getScaledInstance((int) (imagen.getWidth(null) * multiplicador),
							(int) (imagen.getHeight(null) * multiplicador), Image.SCALE_DEFAULT),
					(int) (i * 8 * multiplicador), 0, null);
		}
	}

	public BufferedImage getBufferedImage() {
		return imagen;
	}

	public ImageIcon getImageIcon() {
		return new ImageIcon(imagen);
	}
}
