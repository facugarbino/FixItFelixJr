package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Audio;
import controlador.JuegoMain;
import test.TestJuego;

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
	private PantallaJuego pantallaJuego; 
	private static PantallaMenu INSTANCE;
	private final static double MULTIPLICADOR = JuegoMain.MULTIPLICADOR_MENU;
	
	
	public static PantallaMenu getInstance() {
		if (INSTANCE==null) {
			INSTANCE = new PantallaMenu();
		}
		//Esto es para que el frame se abra en el centro de la pantalla
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				INSTANCE.setLocation(dim.width/2-INSTANCE.getSize().width/2, dim.height/2-INSTANCE.getSize().height/2);
				
		return INSTANCE;
	}

	/**
	 * Create the frame.
	 */
	private PantallaMenu() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 360);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		botonConfiguracion = new JLabel();
		botonConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		botonConfiguracion.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoConfig.png")));
		botonConfiguracion.setBounds(487,12,70,70);
		botonConfiguracion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PantallaConfig.getInstance().setVisible(true);
				setVisible(false);
			}
			public void mouseEntered(MouseEvent e) {
				botonConfiguracion.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoConfig.png")).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
			}
			public void mouseExited(MouseEvent e){
				botonConfiguracion.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/imagenes/iconos/iconoConfig.png")));
			}
		});
		
		botonInstrucciones = new JLabel();
		botonInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		botonInstrucciones.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoInstrucciones.png")));
		botonInstrucciones.setBounds(49, 150, 130, 130);
		botonInstrucciones.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PantallaInstrucciones.getInstance().setVisible(true);
				setVisible(false);
			}
			public void mouseEntered(MouseEvent e) {
				//botonInstrucciones.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoInstrucciones130.png")));
				botonInstrucciones.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoInstrucciones.png")).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT)));
			}
			public void mouseExited(MouseEvent e){
				botonInstrucciones.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoInstrucciones.png")));
			}
		});
		
		botonJugar = new JLabel();
		botonJugar.setHorizontalAlignment(SwingConstants.CENTER);
		botonJugar.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/imagenes/iconos/iconoPlay.png")));
		botonJugar.setBounds(228, 150, 130, 130);
		botonJugar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JuegoMain.comenzarJuego();
			}
			public void mouseEntered(MouseEvent e) {
				//botonJugar.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/iconoPlay130.png")));
				botonJugar.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoPlay.png")).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT)));
			}
			public void mouseExited(MouseEvent e){
				botonJugar.setIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/imagenes/iconos/iconoPlay.png")));
			}
		});
		
		botonRanking = new JLabel();
		botonRanking.setHorizontalAlignment(SwingConstants.CENTER);
		botonRanking.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoRanking.png")));
		botonRanking.setBounds(407, 150, 130, 130);
		botonRanking.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PantallaRanking.getInstance().setVisible(true);
				Audio.getInstance().inicioDeJuego();
				setVisible(false);
			}
			public void mouseEntered(MouseEvent e) {
				//botonRanking.setIcon(new ImageIcon(getClass().getResource("/recursos/iconoRanking130.png")));
				botonRanking.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoRanking.png")).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT)));
			}
			public void mouseExited(MouseEvent e){
				botonRanking.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoRanking.png")));
			}
		});
		
		titulo = new JLabel();
		titulo.setBounds(68, 12, 450, 129);
		titulo.setIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoTitulo.png")));
		contentPane.add(botonJugar);
		contentPane.add(botonInstrucciones);
		contentPane.add(botonRanking);
		contentPane.add(botonConfiguracion);
		contentPane.add(titulo);
		Audio.getInstance().apertura();
	}
}
