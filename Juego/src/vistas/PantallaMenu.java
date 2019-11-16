package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Audio;
import controlador.JuegoMain;
import utils.ColorDeLetra;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PantallaMenu extends JFrame {

	private JPanel contentPane;
	private JLabel botonJugar;
	private JLabel botonInstrucciones;
	private JLabel botonRanking;
	private JLabel botonConfiguracion;
	private JLabel titulo;
	private JLabel copyright;
	private static PantallaMenu INSTANCE;
	private final static double MULTIPLICADOR = JuegoMain.MULTIPLICADOR_MENU;
	private ImageIcon imgRanking;
	private ImageIcon imgRanking2;
	private ImageIcon imgInstrucciones;
	private ImageIcon imgInstrucciones2;
	private ImageIcon imgJugar;
	private ImageIcon imgJugar2;
	private ImageIcon imgConfig;
	private ImageIcon imgConfig2;
	private ImageIcon imgCopyRight;
	private ImageIcon imgTitulo;

	private void setBounds2(JComponent comp, int x1, int y1, int x2, int y2) {
		comp.setBounds((int)(x1*MULTIPLICADOR), (int)(y1*MULTIPLICADOR),
				(int)(x2*MULTIPLICADOR), (int)(y2*MULTIPLICADOR));
	}

	public static PantallaMenu getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PantallaMenu();
		}
		// Esto es para que el frame se abra en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		INSTANCE.setLocation(dim.width / 2 - INSTANCE.getSize().width / 2,
				dim.height / 2 - INSTANCE.getSize().height / 2);

		return INSTANCE;
	}

	/**
	 * Create the frame.
	 */
	private PantallaMenu() {
		// Cargamos las imagenes

		imgRanking = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoRanking.png"))
				.getImage()
				.getScaledInstance((int) (100 * MULTIPLICADOR), (int) (100 * MULTIPLICADOR), Image.SCALE_DEFAULT));
		imgRanking2 = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoRanking.png"))
				.getImage()
				.getScaledInstance((int) (130 * MULTIPLICADOR), (int) (130 * MULTIPLICADOR), Image.SCALE_DEFAULT));
		imgJugar = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoPlay.png"))
				.getImage()
				.getScaledInstance((int) (100 * MULTIPLICADOR), (int) (100 * MULTIPLICADOR), Image.SCALE_DEFAULT));
		imgJugar2 = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoPlay.png"))
				.getImage()
				.getScaledInstance((int) (130 * MULTIPLICADOR), (int) (130 * MULTIPLICADOR), Image.SCALE_DEFAULT));
		imgConfig = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoConfig.png"))
				.getImage()
				.getScaledInstance((int) (50 * MULTIPLICADOR), (int) (50 * MULTIPLICADOR), Image.SCALE_DEFAULT));
		imgConfig2 = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoConfig.png"))
				.getImage()
				.getScaledInstance((int) (70 * MULTIPLICADOR), (int) (70 * MULTIPLICADOR), Image.SCALE_DEFAULT));
		imgInstrucciones = new ImageIcon(
				new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoInstrucciones.png")).getImage()
						.getScaledInstance((int) (100 * MULTIPLICADOR), (int) (100 * MULTIPLICADOR),
								Image.SCALE_DEFAULT));
		imgInstrucciones2 = new ImageIcon(
				new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoInstrucciones.png")).getImage()
						.getScaledInstance((int) (130 * MULTIPLICADOR), (int) (130 * MULTIPLICADOR),
								Image.SCALE_DEFAULT));
		imgCopyRight = new ImagenTextual("facundo garbino - uciel rodriguez murphy", 1.5, ColorDeLetra.ROJO)
				.getImageIcon();
		imgTitulo = new ImageIcon(new ImageIcon(getClass().getResource("/recursos/imagenes/iconos/iconoTitulo.png"))
				.getImage()
				.getScaledInstance((int) (450 * MULTIPLICADOR), (int) (129 * MULTIPLICADOR), Image.SCALE_DEFAULT));

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 586, 380);
		setBounds((int) (100 * MULTIPLICADOR), (int) (100 * MULTIPLICADOR), (int) (586 * MULTIPLICADOR),
				(int) (380 * MULTIPLICADOR));

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		copyright = new JLabel();
		copyright.setIcon(imgCopyRight);
		// copyright.setBounds(59,315,468,30);
		setBounds2(copyright, 45, 307, 478,29);

		contentPane.add(copyright);
		botonConfiguracion = new JLabel();
		botonConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		botonConfiguracion.setIcon(imgConfig);
		// botonConfiguracion.setBounds(487,12,70,70);
		setBounds2(botonConfiguracion, 487, 12, 70, 70);
		botonConfiguracion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PantallaConfig.getInstance().setVisible(true);
				setVisible(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonConfiguracion.setIcon(imgConfig2);
			}

			public void mouseExited(MouseEvent e) {
				botonConfiguracion.setIcon(imgConfig);
			}
		});

		botonInstrucciones = new JLabel();
		botonInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		botonInstrucciones.setIcon(imgInstrucciones);
		// botonInstrucciones.setBounds(49, 150, 130, 130);
		setBounds2(botonInstrucciones, 49, 150, 130, 130);
		botonInstrucciones.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PantallaInstrucciones.getInstance().setVisible(true);
				setVisible(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonInstrucciones.setIcon(imgInstrucciones2);
			}

			public void mouseExited(MouseEvent e) {
				botonInstrucciones.setIcon(imgInstrucciones);
			}
		});

		botonJugar = new JLabel();
		botonJugar.setHorizontalAlignment(SwingConstants.CENTER);
		botonJugar.setIcon(imgJugar);
		// botonJugar.setBounds(228, 150, 130, 130);
		setBounds2(botonJugar, 228, 150, 130, 130);
		botonJugar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Audio.getInstance().inicioDeJuego();
				JuegoMain.comenzarJuego();
			}

			public void mouseEntered(MouseEvent e) {
				botonJugar.setIcon(imgJugar2);
			}

			public void mouseExited(MouseEvent e) {
				botonJugar.setIcon(imgJugar);
			}
		});

		botonRanking = new JLabel();
		botonRanking.setHorizontalAlignment(SwingConstants.CENTER);
		botonRanking.setIcon(imgRanking);
		//botonRanking.setBounds(407, 150, 130, 130);
		setBounds2(botonRanking, 407, 150, 130, 130);
		botonRanking.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PantallaRanking.getInstance().setVisible(true);
				setVisible(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonRanking.setIcon(imgRanking2);
			}

			public void mouseExited(MouseEvent e) {
				botonRanking.setIcon(imgRanking);
			}
		});

		titulo = new JLabel();
		// titulo.setBounds(68, 12, 450, 129);
		setBounds2(titulo, 68, 12, 450, 129);
		titulo.setIcon(imgTitulo);

		contentPane.add(botonJugar);
		contentPane.add(botonInstrucciones);
		contentPane.add(botonRanking);
		contentPane.add(botonConfiguracion);
		contentPane.add(titulo);
		Audio.getInstance().apertura();
	}
}
