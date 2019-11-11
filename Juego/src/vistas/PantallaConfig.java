package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.JuegoMain;
import utils.ColorDeLetra;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class PantallaConfig extends JFrame {

	private JPanel contentPane;
	private JLabel titulo;
	private JComboBox<String> comboLetras = new JComboBox<>();
	private JComboBox<Integer> comboNivel = new JComboBox<>();
	private JLabel lblLetras = new JLabel();
	private JLabel lblNivel = new JLabel();
	private static PantallaConfig INSTANCE;
	private final static double MULTIPLICADOR = JuegoMain.MULTIPLICADOR_MENU;

	private void setBounds2(JComponent comp, int x1, int y1, int x2, int y2) {
		comp.setBounds((int)(x1*MULTIPLICADOR), (int)(y1*MULTIPLICADOR),
				(int)(x2*MULTIPLICADOR), (int)(y2*MULTIPLICADOR));
	}
	
	
	private PantallaConfig() {
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
				(int)(614*MULTIPLICADOR), (int)(300*MULTIPLICADOR));
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		titulo = new JLabel();
		setBounds2(titulo,42,25,520,45);
//		titulo.setBounds(42, 25, 520, 45);
		titulo.setIcon(new ImagenTextual("configuracion", 5, ColorDeLetra.CELESTE).getImageIcon());
		contentPane.add(titulo);

		comboLetras.setModel(new DefaultComboBoxModel<String>(new String[] { "↑←↓→", "WASD" }));
		setBounds2(comboLetras,225, 123, 116, 24);
//		comboLetras.setBounds(225, 123, 116, 24);
		comboLetras.setBackground(Color.BLACK);
		comboLetras.setForeground(Color.RED);
		comboLetras.setFocusable(false);
		comboLetras.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) arg0.getItem();
					System.out.println(item);
				}
			}
		});
		contentPane.add(comboLetras);

		comboNivel.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
		setBounds2(comboNivel,276, 176, 116, 24);
//		comboNivel.setBounds(276, 176, 116, 24);
		comboNivel.setBackground(Color.BLACK);
		comboNivel.setForeground(Color.RED);
		comboNivel.setFocusable(false);
		comboNivel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					int item = (int) arg0.getItem();
				}
			}
		});
		contentPane.add(comboNivel);
		setBounds2(lblLetras,42,118,177,34);
//		lblLetras.setBounds(42, 118, 177, 34);
		lblLetras.setIcon(new ImagenTextual("letras a usar:", 1.5, ColorDeLetra.ROJO).getImageIcon());
		contentPane.add(lblLetras);
		setBounds2(lblNivel,42, 176, 216, 24);
//		lblNivel.setBounds(42, 176, 216, 24);
		lblNivel.setIcon(new ImagenTextual("nivel de comienzo:", 1.5, ColorDeLetra.ROJO).getImageIcon());
		contentPane.add(lblNivel);
	}

	public static PantallaConfig getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PantallaConfig();
		}
		//Esto es para que el frame se abra en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		INSTANCE.setLocation(dim.width/2-INSTANCE.getSize().width/2, dim.height/2-INSTANCE.getSize().height/2);
	
		return INSTANCE;

	}

	public JComboBox<Integer> getComboNivel() {
		return comboNivel;
	}

	public JComboBox<String> getComboLetras() {
		return comboLetras;
	}
}
