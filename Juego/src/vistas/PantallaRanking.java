package vistas;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
		

		tabla = new JTable();
		tabla.setShowGrid(false);
		tabla.setEnabled(false);
		tabla.setRowHeight(70);
		tabla.setBackground(Color.BLACK);
		tabla.setBounds(25, 100, 700, 400);
		actualizar();
		contentPane.add(tabla);
		
		titulo = new JLabel();
		titulo.setBounds(235, 25, 280, 45);
		titulo.setIcon(new ImagenTextual("ranking", 5, ColorDeLetra.CELESTE).getImageIcon());
		contentPane.add(titulo);

	}

	private void actualizar() {
		Object[][] rows = new Object[5][3];
		List<HighScore> tops = ranking.getTop5();
		int tamaño = 3;
		for (int i=0;i<tops.size();i++) {
			rows[i][0] = new ImagenTextual(Integer.toString(i+1), tamaño, ColorDeLetra.ROJO).getImageIcon();
			rows[i][1] = new ImagenTextual(tops.get(i).getNombre(), tamaño, ColorDeLetra.ROJO).getImageIcon();
			rows[i][2] = new ImagenTextual(Long.toString(tops.get(i).getPuntaje()), tamaño, ColorDeLetra.ROJO).getImageIcon();
		}
		String[] columns = {"numero","nombre","puntaje"};
		tabla.setModel( new DefaultTableModel(rows, columns) {			
			 @Override
			    public Class<?> getColumnClass(int column) {
				 return ImageIcon.class;
			    }
		});
		tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(450);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
	}
}
