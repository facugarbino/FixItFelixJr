package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import juego.Juego;

@SuppressWarnings("serial")
public class PantallaJuego extends JFrame {

	private JPanel contentPane;
	private Juego juego;
	
	
	/**
	 * Create the frame.
	 */
	public PantallaJuego() {
		juego = Juego.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	}
	
	public void dibujarComponentes() {
		
	}
	
	public void paintComponents(Graphics g) {
		
	}

}
