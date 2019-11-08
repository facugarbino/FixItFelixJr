package vistas;

import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controlador.AdaptadorFlechas;
import controlador.AdaptadorWASD;
import controlador.JuegoMain;
import juego.Juego;
import utils.ColorDeLetra;
import utils.Contador;
import utils.Orientacion;

import javax.swing.JButton;

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
		juego = Juego.getInstance();
		if (PantallaConfig.getInstance().getComboLetras().getSelectedIndex() == 0) {
			addKeyListener(new AdaptadorFlechas());
		} else {
			addKeyListener(new AdaptadorWASD());
		}
		scroll = new JScrollPane();
		// scroll.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		scroll.setBounds(0, 50, 420, 400);
		scroll.setBorder(BorderFactory.createEmptyBorder());
//		scroll.setBounds(0, 50, 420, 800);
		panelMapa = new PanelEdificio();
		panelInfo = new PanelInfo();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 420, 482);
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
		pointScroll = new Point(0, scroll.getHeight());
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
		contentPane.setBorder(new EmptyBorder(0, 0, 420, 500));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		// scrollear(-200);
		nroSeccion = juego.getMapa().getEdificio().getSeccionActual().getNroSeccion();
	}

	public void scrollearUp(int pixeles) {
		Contador timer = new Contador(10);
		int nuevoY = (int) (scroll.getViewport().getViewPosition().getY()-pixeles);
		if (nuevoY<0) {
			nuevoY=0;
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
		int seccionNueva;
		if (nroSeccion != (seccionNueva = juego.getMapa().getEdificio().getSeccionActual().getNroSeccion())) {
			nroSeccion = seccionNueva;
			scrollearUp(166);
		}
	}

}
