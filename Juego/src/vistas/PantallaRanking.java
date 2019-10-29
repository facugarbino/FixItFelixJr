package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import juego.Juego;
import ranking.Ranking;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PantallaRanking extends JFrame {

	private JPanel contentPane;
	private Ranking ranking;
	private static PantallaRanking INSTANCE;
	private JTable table;

	public static PantallaRanking getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PantallaRanking();
		}
		return INSTANCE;
	}

	/**
	 * Create the frame.
	 */
	private PantallaRanking() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		ranking = Juego.getInstance().getRanking();
		// Le ponemos DO_NOTHING porque el cierre lo manejamos nosotros con el
		// WindowListener
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		//getGraphics().drawImage((new ImagenTextual("abc").getBufferedImage()), 0, 0, null);
		

		table = new JTable();
		// table.setModel(new );
		table.setBounds(106, 53, 511, 337);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 12, 66, 15);
		lblNewLabel.setIcon(new ImagenTextual("abcaccbabccbacb").getImageIcon());
		contentPane.add(lblNewLabel);
	}
}
