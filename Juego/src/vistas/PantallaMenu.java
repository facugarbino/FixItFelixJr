package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PantallaMenu extends JFrame {

	private JPanel contentPane;
	private JLabel botonJugar;
	private JLabel botonInstrucciones;
	private JLabel botonRanking;
	private JLabel botonConfiguracion;	
	private JLabel titulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaMenu frame = new PantallaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 360);
		//Esto es para que el frame se abra en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		botonConfiguracion = new JLabel();
		botonConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		botonConfiguracion.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/iconoConfig.png")));
		botonConfiguracion.setBounds(487,12,70,70);
		botonConfiguracion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
				botonConfiguracion.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/iconoConfig70.png")));
			}
			public void mouseExited(MouseEvent e){
				botonConfiguracion.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/iconoConfig.png")));
			}
		});
		
		botonInstrucciones = new JLabel();
		botonInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		botonInstrucciones.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoInstrucciones.png")));
		botonInstrucciones.setBounds(49, 150, 130, 130);
		botonInstrucciones.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
				botonInstrucciones.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoInstrucciones130.png")));
			}
			public void mouseExited(MouseEvent e){
				botonInstrucciones.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoInstrucciones.png")));
			}
		});
		
		botonJugar = new JLabel();
		botonJugar.setHorizontalAlignment(SwingConstants.CENTER);
		botonJugar.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/iconoPlay.png")));
		botonJugar.setBounds(228, 150, 130, 130);
		botonJugar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
				botonJugar.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/iconoPlay130.png")));
			}
			public void mouseExited(MouseEvent e){
				botonJugar.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/iconoPlay.png")));
			}
		});
		
		botonRanking = new JLabel();
		botonRanking.setHorizontalAlignment(SwingConstants.CENTER);
		botonRanking.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoRanking.png")));
		botonRanking.setBounds(407, 150, 130, 130);
		botonRanking.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PantallaRanking.getInstance().setVisible(true);
			}
			public void mouseEntered(MouseEvent e) {
				botonRanking.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoRanking130.png")));
			}
			public void mouseExited(MouseEvent e){
				botonRanking.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoRanking.png")));
			}
		});
		
		titulo = new JLabel();
		titulo.setBounds(68, 12, 450, 129);
		titulo.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoTitulo.png")));
		contentPane.add(botonJugar);
		contentPane.add(botonInstrucciones);
		contentPane.add(botonRanking);
		contentPane.add(botonConfiguracion);
		contentPane.add(titulo);
	}
}
