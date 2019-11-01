package vistas;

import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import juego.Juego;
import utils.Contador;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PantallaJuego extends JFrame {

	private JPanel contentPane;
	private Juego juego;
	private PanelMapa panelMapa;
	private JScrollPane scroll;
	/**
	 * Create the frame.
	 */
	public PantallaJuego(int nivelDeComienzo) {
		scroll = new JScrollPane();
		//scroll.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		scroll.setBounds(0, 50, 420, 400);
		panelMapa = new PanelMapa();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 420, 482);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.add(scroll);
		scroll.setViewportView(panelMapa);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//		
//		JScrollBar verticalBar = scroll.getVerticalScrollBar();
//		verticalBar.addAdjustmentListener(new AdjustmentListener() {
//	        @Override
//	        public void adjustmentValueChanged(AdjustmentEvent e) {
//	            Adjustable adjustable = e.getAdjustable();
//	            adjustable.setValue(adjustable.getMinimum());
//	            verticalBar.removeAdjustmentListener(this);
//	        }
//	    });
		contentPane.setBorder(new EmptyBorder(0,0,420,500));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
	}
	
	public void scrollear(int pixeles) {
		Contador timer = new Contador(10);
		int actual = scroll.getVerticalScrollBar().getValue(); 
		for (int i=actual;i>actual+pixeles;i--) {
			while (!timer.contar());
			timer.resetear();
			System.out.println("entre");
			scroll.getVerticalScrollBar().setValue(i);
		}
	}
	
}
