package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import controlador.AdaptadorFlechas;
import controlador.AdaptadorWASD;
import controlador.JuegoMain;
import juego.Edificio;
import juego.Juego;
import utils.Contador;
import utils.Posicion;

@SuppressWarnings("serial")
public class PantallaJuego extends JFrame {

	private JPanel contentPane;
	private Juego juego;
	private PanelEdificio panelMapa;
	private JScrollPane scroll;
	private PanelInfo panelInfo;
	private Point pointScroll;
	private int nroSeccion;

	/**
	 * Create the frame.
	 */
	public PantallaJuego() {
		setResizable(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//PREGUNTAR
				
				//PantallaMenu.getInstance().setVisible(true);
			}
			public void windowIconified(WindowEvent e) {
				juego.pausar();
			}
			
		});
		
		juego = Juego.getInstance();
		if (PantallaConfig.getInstance().getComboLetras().getSelectedIndex() == 0) {
			addKeyListener(new AdaptadorFlechas());
		} else {
			addKeyListener(new AdaptadorWASD());
		}
		scroll = new JScrollPane();
		// scroll.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		scroll.setBounds(0, (int)(50*JuegoMain.MULTIPLICADOR), 
				(int)(Edificio.ANCHO*2*JuegoMain.MULTIPLICADOR), (int)(250*JuegoMain.MULTIPLICADOR));
		scroll.setBorder(BorderFactory.createEmptyBorder());
//		scroll.setBounds(0, 50, 420, 800);
		panelMapa = new PanelEdificio();
		panelInfo = new PanelInfo();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, (int)(Edificio.ANCHO*2*JuegoMain.MULTIPLICADOR), (int)(330*JuegoMain.MULTIPLICADOR));
//		setBounds(0, 50, 420, 802);
		// Esto es para que el frame se abra en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.add(scroll);
		contentPane.add(panelInfo);

		scroll.setViewportView(panelMapa);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		pointScroll = new Point(0, (int)(Edificio.ALTO*JuegoMain.MULTIPLICADOR) - scroll.getHeight());
		scroll.getViewport().setViewPosition(pointScroll);

		// scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
//		JScrollBar verticalBar = scroll.getVerticalScrollBar();
//		verticalBar.setValue(value);
//		
//		verticalBar.addAdjustmentListener(new AdjustmentListener() {
//	        @Override
//	        public void adjustmentValueChanged(AdjustmentEvent e) {
//	            Adjustable adjustable = e.getAdjustable();
//	            adjustable.setValue(adjustable.getMinimum());
//	            verticalBar.removeAdjustmentListener(this);
//	        }
//	    });
		contentPane.setBorder(new EmptyBorder(0, 0, (int)(420*JuegoMain.MULTIPLICADOR), (int)(500*JuegoMain.MULTIPLICADOR)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		// scrollear(-200);
		nroSeccion = juego.getMapa().getEdificio().getSeccionActual().getNroSeccion();
	}

	public Thread scrollHacia(Posicion posicion) {
		int yDeseado = (int)(Edificio.ALTO*JuegoMain.MULTIPLICADOR) - (int)(250*JuegoMain.MULTIPLICADOR) - (int)(posicion.getY()*JuegoMain.MULTIPLICADOR);
		System.out.println(yDeseado);
		Thread t = new Thread(new Runnable() {
			public void run() {
				final boolean[] terminado = new boolean[1];
				JViewport viewport = scroll.getViewport();
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					int actualY = (int) viewport.getViewPosition().getY();
					public void run() {
						System.out.println(actualY);
						if (actualY > yDeseado) {
							actualY-=5;
							viewport.setViewPosition(new Point(0, actualY));
							System.out.println("bajo");
						} else if (actualY < yDeseado) {
							actualY+=5;
							System.out.println("subo");
							viewport.setViewPosition(new Point(0, actualY++));
						} else {
							System.out.println("cancelo");
							timer.cancel();
							terminado[0]=true;
						}
					}
				}, 0, 20);
				
				while (!terminado[0]) {
					System.out.println("no terine");
				}
				System.out.println("llegue");
			}
		});
		t.start();
		return t;
	}

	public void scrollearUp(int pixeles) {
		Contador timer = new Contador(10);
		int nuevoY = (int) (scroll.getViewport().getViewPosition().getY() - pixeles);
		if (nuevoY < 0) {
			nuevoY = 0;
		}
		scroll.getViewport().setViewPosition(new Point(0, nuevoY));
//		int actual = scroll.getVerticalScrollBar().getValue(); 
//		System.out.println(actual);
//		for (int i=actual;i>actual+pixeles;i--) {
//			while (!timer.contar());
//			timer.resetear();
//			System.out.println("entre");
//			scroll.getVerticalScrollBar().setValue(i);
//		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
	}

}
