package graficador.vista;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;

import graficador.modelo.Dibujable;
import graficador.modelo.InformacionDibujable;
import juego.Juego;
import juego.Mapa;
import utils.Orientacion;

/*
 * Muestra en pantalla elementos. Por ejemplo, si tengo varios personajes (implementan la 
 * interfaz Dibujable), sólo necesito su posicón y un caracter representativo para mostrarlo
 * */
public class Graficador {
   private static final int ANCHO = Mapa.ANCHO;
   private static final int ALTO = Mapa.ALTO;
   private static JFrame frame = new JFrame("Visualización Fix It Felix");
   private static final int margen = 50;
   private static Juego juego = Juego.getJuego();
   private static Graphics gr;
   
   static {
      frame.setSize(300, 400);
      frame.setVisible(true);
      frame.setResizable(false);
      gr = frame.getContentPane().getGraphics();
      //Investigamos y agregamos este Listener para mover a Felix
      //así se puede ver cómo funciona el resto del juego,
      //pasando de sección y de nivel.
      frame.addKeyListener(new KeyListener() {
	
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			   switch (key) {
			   case (KeyEvent.VK_LEFT): juego.moverFelix(Orientacion.IZQUIERDA); break; 
			   case (KeyEvent.VK_RIGHT): juego.moverFelix(Orientacion.DERECHA); break;
			   case (KeyEvent.VK_UP): juego.moverFelix(Orientacion.ARRIBA); break;
			   case (KeyEvent.VK_DOWN): juego.moverFelix(Orientacion.ABAJO); break;
			   case (KeyEvent.VK_SPACE): juego.darMartillazo(); break;
			   case (KeyEvent.VK_P): juego.pausar(); break;
			   }
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
    	  
      });
   }

   public static void refrescarTopDown(List<? extends Dibujable> listaDibujables) {
      Iterator var4 = listaDibujables.iterator();
      gr.clearRect(0, 0, ANCHO + margen, ALTO + margen);
      while(var4.hasNext()) {
         Dibujable i = (Dibujable)var4.next();
         InformacionDibujable id = i.getInformacionDibujable();
         gr.drawString(id.getRepresentacion().toString(), id.getX() + margen, ALTO-(id.getY() + margen));
      }
      gr.drawString("Nivel: "+ juego.getNroNivel(), 5, 20);
      gr.drawString("Tiempo: "+ juego.getTiempo(), 60, 20);

      
   }
}