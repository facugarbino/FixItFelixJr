package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.JuegoMain;
import utils.ColorDeLetra;

@SuppressWarnings("serial")
public class PantallaInstrucciones extends JFrame {

	private JPanel contentPane;
	private JLabel titulo;
	private JLabel ventanilla = new JLabel();
	private JLabel introLetras1 = new JLabel();
	private JLabel introLetras2 = new JLabel();
	private JLabel evitaLetras = new JLabel();
	private JLabel agarraLetras = new JLabel();
	private JLabel moveteLetras = new JLabel();
	private JLabel arreglaLetras = new JLabel();
	private static PantallaInstrucciones INSTANCE;
	private final static double MULTIPLICADOR = JuegoMain.MULTIPLICADOR_MENU;

	
	private void setBounds2(JComponent comp, int x1, int y1, int x2, int y2) {
		comp.setBounds((int)(x1*MULTIPLICADOR), (int)(y1*MULTIPLICADOR),
				(int)(x2*MULTIPLICADOR), (int)(y2*MULTIPLICADOR));
	}
	
	private PantallaInstrucciones() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				PantallaMenu.getInstance().setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds((int)(100*MULTIPLICADOR), (int)(100*MULTIPLICADOR),
				(int)(900*MULTIPLICADOR),(int)(600*MULTIPLICADOR));
		//setBounds(100, 100, 900,600);
		// Esto es para que el frame se abra en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		titulo = new JLabel();
		setBounds2(titulo,182, 65, 536, 54);
		//titulo.setBounds(182, 65, 536, 54);
		titulo.setIcon(new ImagenTextual("Instrucciones", 5, ColorDeLetra.CELESTE).getImageIcon());
		contentPane.add(titulo);

		int tamaño = 2;
		setBounds2(introLetras1,92, 152, 869, 27);
//		introLetras1.setBounds(92, 152, 869, 27);
		introLetras1
				.setIcon(new ImagenTextual("arregla todas las ventanas rotas en cada piso", tamaño, ColorDeLetra.VERDE)
						.getImageIcon());
		contentPane.add(introLetras1);

		setBounds2(introLetras2,92, 173, 739, 27);
//		introLetras2.setBounds(92, 173, 739, 27);
		introLetras2
				.setIcon(new ImagenTextual("para avanzar al proximo nivel", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(introLetras2);

		setBounds2(evitaLetras,121, 279, 94, 50);
//		evitaLetras.setBounds(121, 279, 94, 50);
		evitaLetras.setIcon(new ImagenTextual("evita", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(evitaLetras);

		setBounds2(agarraLetras,121, 375, 144, 50);
//		agarraLetras.setBounds(121, 375, 144, 50);
		agarraLetras.setIcon(new ImagenTextual("agarra", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(agarraLetras);

		setBounds2(moveteLetras,637, 242, 106, 50);
//		moveteLetras.setBounds(637, 242, 106, 50);
		moveteLetras.setIcon(new ImagenTextual("movete", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(moveteLetras);

		setBounds2(arreglaLetras,669, 403, 127, 50);
//		arreglaLetras.setBounds(669, 403, 127, 50);
		arreglaLetras.setIcon(new ImagenTextual("arregla", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(arreglaLetras);

		ventanilla.setIcon(new ImageIcon(
				new ImageIcon(PantallaMenu.class.getResource("/recursos/imagenes/extra/imagenInstrucciones.png")).getImage()
						.getScaledInstance((int)(900*MULTIPLICADOR),
								(int)(600*MULTIPLICADOR), Image.SCALE_DEFAULT)));
		setBounds2(ventanilla,5, 0, 900, 550);
//		ventanilla.setBounds(5, 0, 900, 550);
		contentPane.add(ventanilla);

	}

	public static PantallaInstrucciones getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}
		return (INSTANCE = new PantallaInstrucciones());

	}

}
