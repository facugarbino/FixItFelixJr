package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.ColorDeLetra;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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

	/**
	 * Create the frame.
	 */
	private PantallaConfig() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 614, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		titulo = new JLabel();
		titulo.setBounds(42, 25, 520, 45);
		titulo.setIcon(new ImagenTextual("configuracion", 5, ColorDeLetra.CELESTE).getImageIcon());
		contentPane.add(titulo);

		comboLetras.setModel(new DefaultComboBoxModel<String>(new String[] { "↑←↓→", "WASD" }));
		comboLetras.setBounds(225, 123, 116, 24);
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

		comboNivel.setModel(
				new DefaultComboBoxModel<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
		comboNivel.setBounds(276, 176, 116, 24);
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

		lblLetras.setBounds(42, 118, 177, 34);
		lblLetras.setIcon(new ImagenTextual("letras a usar:", 1.5, ColorDeLetra.ROJO).getImageIcon());
		contentPane.add(lblLetras);

		lblNivel.setBounds(42, 176, 216, 24);
		lblNivel.setIcon(new ImagenTextual("nivel de comienzo:", 1.5, ColorDeLetra.ROJO).getImageIcon());
		contentPane.add(lblNivel);
	}

	public static PantallaConfig getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}
		return (INSTANCE = new PantallaConfig());

	}
	
	public JComboBox<Integer> getComboNivel(){
		return comboNivel;
	}
	public JComboBox<String> getComboLetras(){
		return comboLetras;
	}
}
