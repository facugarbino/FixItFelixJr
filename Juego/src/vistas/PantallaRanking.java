package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.JuegoMain;
import juego.Juego;
import ranking.HighScore;
import ranking.Ranking;
import utils.ColorDeLetra;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PantallaRanking extends JFrame {

	private JPanel contentPane;
	private Ranking ranking;
	private static PantallaRanking INSTANCE;
	private JTable tabla;
	private JLabel titulo;
	private final static double MULTIPLICADOR = JuegoMain.MULTIPLICADOR_MENU;

	public static PantallaRanking getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PantallaRanking();
		}
		INSTANCE.actualizar();
		return INSTANCE;
	}

	/**
	 * Create the frame.
	 */
	private PantallaRanking() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				PantallaMenu.getInstance().setVisible(true);
			}
		});
		ranking = Juego.getInstance().getRanking();
		// Le ponemos DO_NOTHING porque el cierre lo manejamos nosotros con el
		// WindowListener
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, (int)(750*MULTIPLICADOR), (int)(500*MULTIPLICADOR));

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		// getGraphics().drawImage((new ImagenTextual("abc").getBufferedImage()), 0, 0,
		// null);

		tabla = new JTable();
		tabla.setShowGrid(false);
		tabla.setEnabled(false);
		tabla.setRowHeight((int)(70*MULTIPLICADOR));
		tabla.setBackground(Color.BLACK);
		tabla.setBounds((int)(25*MULTIPLICADOR), (int)(100*MULTIPLICADOR), 
				(int)(700*MULTIPLICADOR), (int)(400*MULTIPLICADOR));
		actualizar();
		contentPane.add(tabla);

		titulo = new JLabel();
		titulo.setBounds((int)(235*MULTIPLICADOR), (int)(25*MULTIPLICADOR),
				(int)(280*MULTIPLICADOR), (int)(45*MULTIPLICADOR));
		titulo.setIcon(new ImagenTextual("ranking", 5, ColorDeLetra.CELESTE).getImageIcon());
		contentPane.add(titulo);

	}

	private void actualizar() {
		Object[][] rows = new Object[5][3];
		List<HighScore> tops = ranking.getTop5();
		int tamaño = 3;
		for (int i = 0; i < tops.size(); i++) {
			rows[i][0] = new ImagenTextual(Integer.toString(i + 1), tamaño, ColorDeLetra.ROJO).getImageIcon();
			rows[i][1] = new ImagenTextual(tops.get(i).getNombre(), tamaño, ColorDeLetra.ROJO).getImageIcon();
			rows[i][2] = new ImagenTextual(Long.toString(tops.get(i).getPuntaje()), tamaño, ColorDeLetra.ROJO)
					.getImageIcon();
		}
		String[] columns = { "numero", "nombre", "puntaje" };
		tabla.setModel(new DefaultTableModel(rows, columns) {
			@Override
			public Class<?> getColumnClass(int column) {
				return ImageIcon.class;
			}
		});
		tabla.getColumnModel().getColumn(0).setPreferredWidth((int)(50*MULTIPLICADOR));
		tabla.getColumnModel().getColumn(1).setPreferredWidth((int)(500*MULTIPLICADOR));
		tabla.getColumnModel().getColumn(2).setPreferredWidth((int)(150*MULTIPLICADOR));
		// Esto es para que el frame se abra en el centro de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}
}
