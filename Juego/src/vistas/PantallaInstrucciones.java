package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

	private PantallaInstrucciones() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				PantallaMenu.getInstance().setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		// Esto es para que el frame se abra en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		titulo = new JLabel();
		titulo.setBounds(182, 65, 536, 54);
		titulo.setIcon(new ImagenTextual("Instrucciones", 5, ColorDeLetra.CELESTE).getImageIcon());
		contentPane.add(titulo);

		int tamaño = 2;
		introLetras1.setBounds(92, 152, 869, 27);
		introLetras1
				.setIcon(new ImagenTextual("arregla todas las ventanas rotas en cada piso", tamaño, ColorDeLetra.VERDE)
						.getImageIcon());
		contentPane.add(introLetras1);

		introLetras2.setBounds(92, 173, 739, 27);
		introLetras2
				.setIcon(new ImagenTextual("para avanzar al proximo nivel", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(introLetras2);

		evitaLetras.setBounds(121, 279, 94, 50);
		evitaLetras.setIcon(new ImagenTextual("evita", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(evitaLetras);

		agarraLetras.setBounds(121, 375, 144, 50);
		agarraLetras.setIcon(new ImagenTextual("agarra", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(agarraLetras);

		moveteLetras.setBounds(637, 242, 106, 50);
		moveteLetras.setIcon(new ImagenTextual("movete", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(moveteLetras);

		arreglaLetras.setBounds(669, 403, 127, 50);
		arreglaLetras.setIcon(new ImagenTextual("arregla", tamaño, ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(arreglaLetras);

		ventanilla.setIcon(new ImageIcon(
				new ImageIcon(PantallaMenu.class.getResource("/recursos/imagenes/imagenInstrucciones.png")).getImage()
						.getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
		ventanilla.setBounds(5, 0, 900, 550);
		contentPane.add(ventanilla);

	}

	public static PantallaInstrucciones getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}
		return (INSTANCE = new PantallaInstrucciones());

	}

}
