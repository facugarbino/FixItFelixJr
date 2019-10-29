package vistas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagenTextual {
	private BufferedImage imagen;
	public ImagenTextual(String texto) {
		imagen = new BufferedImage(8*texto.length(),9, BufferedImage.TYPE_INT_RGB);
		Graphics g = imagen.getGraphics();
		try {
			texto = texto.toLowerCase();
			for (int i=0;i<texto.length();i++) {
				g.drawImage(ImageIO.read(getClass().getResource("/recursos/letras/"+ texto.charAt(i) + ".png")), i*8, 0, null);
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
