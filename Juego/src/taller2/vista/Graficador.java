package taller2.vista;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

import paquete.Juego;
import paquete.Mapa;
import paquete.Orientacion;
import taller2.modelo.Dibujable;
import taller2.modelo.InformacionDibujable;

/*
 * Muestra en pantalla elementos. Por ejemplo, si tengo varios personajes (implementan la 
 * interfaz Dibujable), sólo necesito su posicón y un caracter representativo para mostrarlo
 * */
public class Graficador {
   private static final int ANCHO = Mapa.ANCHO;
   private static final int ALTO = Mapa.ALTO;
   private static final int DELTA = 50;
   private static JFrame frame = new JFrame("Visualización Fix It Felix");
   private static final int margen = 50;

   static {
      frame.setSize(300, 400);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent arg0) {
			 int keyCode = arg0.getKeyCode();
			 Juego j = Juego.getJuego();
		        switch(keyCode) {
		        case KeyEvent.VK_UP: j.moverFelix(Orientacion.ARRIBA); break;
		        case KeyEvent.VK_DOWN: j.moverFelix(Orientacion.ABAJO); break;
		        case KeyEvent.VK_LEFT: j.moverFelix(Orientacion.IZQUIERDA); break;
		        case KeyEvent.VK_RIGHT: j.moverFelix(Orientacion.DERECHA); break;
		        case KeyEvent.VK_ENTER: j.darMartillazo(); break;
		        case KeyEvent.VK_P: j.pausar(); break;
		        }
		            
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	  
      });
   }

   public static void refrescarTopDown(List<? extends Dibujable> listaDibujables, int delayMilis) {
      Graphics gr = frame.getContentPane().getGraphics();
      Iterator var4 = listaDibujables.iterator();
      gr.clearRect(0, 0, ANCHO + margen, ALTO + margen);
      while(var4.hasNext()) {
         Dibujable i = (Dibujable)var4.next();
         InformacionDibujable id = i.getInformacionDibujable();
         gr.drawString(id.getRepresentacion().toString(), id.getX() + margen, id.getY() + margen);
      }

      try {
         TimeUnit.MILLISECONDS.sleep((long)delayMilis);
      } catch (InterruptedException var6) {
         var6.printStackTrace();
      }

      
   }

   public static void refrescarDownTop(List<? extends Dibujable> listaDibujables, int delayMilis) {
      Graphics gr = frame.getContentPane().getGraphics();
      Iterator var4 = listaDibujables.iterator();
      gr.clearRect(0, 0, ANCHO + margen, ALTO + margen);
      while(var4.hasNext()) {
         Dibujable i = (Dibujable)var4.next();
         InformacionDibujable id = i.getInformacionDibujable();
         gr.drawString(id.getRepresentacion().toString(), id.getX() + margen, 500 - id.getY());
      }

      try {
         TimeUnit.MILLISECONDS.sleep((long)delayMilis);
      } catch (InterruptedException var6) {
         var6.printStackTrace();
      }

      
   }
}