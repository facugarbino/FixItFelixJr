package vistas;

import java.awt.Color;
import java.awt.Image;
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
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		titulo = new JLabel();
		titulo.setBounds(138, 65, 624, 54);
		titulo.setIcon(new ImagenTextual("Instrucciones",6,ColorDeLetra.CELESTE).getImageIcon());
		contentPane.add(titulo);
		
		int tamaño=2;
		introLetras1.setBounds(92,152,869,27);
		introLetras1.setIcon(new ImagenTextual("arregla todas las ventanas rotas en cada piso",tamaño,ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(introLetras1);
		
		introLetras2.setBounds(92,173,739,27);
		introLetras2.setIcon(new ImagenTextual("para avanzar al proximo nivel",tamaño,ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(introLetras2);
		
		evitaLetras.setBounds(120,295,94,50);
		evitaLetras.setIcon(new ImagenTextual("evita",tamaño,ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(evitaLetras);
		
		agarraLetras.setBounds(92,403,144,50);
		agarraLetras.setIcon(new ImagenTextual("agarra",tamaño,ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(agarraLetras);
		
		moveteLetras.setBounds(645,252,153,50);
		moveteLetras.setIcon(new ImagenTextual("movete",tamaño,ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(moveteLetras);
		
		arreglaLetras.setBounds(669,403,175,50);
		arreglaLetras.setIcon(new ImagenTextual("arregla",tamaño,ColorDeLetra.VERDE).getImageIcon());
		contentPane.add(arreglaLetras);
		
		ventanilla.setIcon(new ImageIcon(new ImageIcon(PantallaMenu.class.getResource("/recursos/imagenes/imagenInstrucciones.png")).getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
		ventanilla.setBounds(5,0,900,600);
		contentPane.add(ventanilla);
		
	}
	
	public static PantallaInstrucciones getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}
		return (INSTANCE = new PantallaInstrucciones());

	}

}
