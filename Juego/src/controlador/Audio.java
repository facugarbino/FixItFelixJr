package controlador;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	private Clip apertura;
	private Clip arregloPanel;
	private Clip choqueLadrillo;
	private Clip comerPastel;
	private Clip inicioDeJuego;
	private Clip fondo;
	private Clip bloqueado;
	private Clip levelUp;
	private Clip perdio;
	private Clip seccionUp;
	private static Audio INSTANCE = null;
	private boolean activado;

	public void setActivado() {
		activado = !activado;
	}
	
	public boolean getActivado() {
		return activado;
	}

	public static Audio getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}
		return (INSTANCE = new Audio());
	}

	private Audio() {
		activado=true;
		try {
			apertura = AudioSystem.getClip();
			arregloPanel = AudioSystem.getClip();
			choqueLadrillo = AudioSystem.getClip();
			comerPastel = AudioSystem.getClip();
			inicioDeJuego = AudioSystem.getClip();
			fondo = AudioSystem.getClip();
			bloqueado = AudioSystem.getClip();
			levelUp = AudioSystem.getClip();
			perdio = AudioSystem.getClip();
			seccionUp = AudioSystem.getClip();
		} catch (LineUnavailableException | IllegalArgumentException e) {
			System.out.println("Error. No se podrá jugar con audio.");
		}

		URL urlApertura = getClass().getResource("/recursos/audio/musicaApertura.wav");
		URL urlArregloPanel = getClass().getResource("/recursos/audio/arregloPanel.wav");
		URL urlChoqueLadrillo = getClass().getResource("/recursos/audio/choqueLadrillo.wav");
		URL urlComerPastel = getClass().getResource("/recursos/audio/comerPastel.wav");
		URL urlInicioDeJuego = getClass().getResource("/recursos/audio/inicioDeJuego.wav");
		URL urlFondo = getClass().getResource("/recursos/audio/musicaFondo.wav");
		URL urlBLoqueado = getClass().getResource("/recursos/audio/bloqueado.wav");
		URL urlLevelUp = getClass().getResource("/recursos/audio/levelUp.wav");
		URL urlPerdio = getClass().getResource("/recursos/audio/perdio.wav");
		URL urlSeccionUp = getClass().getResource("/recursos/audio/seccionUp.wav");

		try {
			apertura.open(AudioSystem.getAudioInputStream(urlApertura));
			choqueLadrillo.open(AudioSystem.getAudioInputStream(urlChoqueLadrillo));
			comerPastel.open(AudioSystem.getAudioInputStream(urlComerPastel));
			inicioDeJuego.open(AudioSystem.getAudioInputStream(urlInicioDeJuego));
			arregloPanel.open(AudioSystem.getAudioInputStream(urlArregloPanel));
			fondo.open(AudioSystem.getAudioInputStream(urlFondo));
			bloqueado.open(AudioSystem.getAudioInputStream(urlBLoqueado));
			levelUp.open(AudioSystem.getAudioInputStream(urlLevelUp));
			perdio.open(AudioSystem.getAudioInputStream(urlPerdio));
			seccionUp.open(AudioSystem.getAudioInputStream(urlSeccionUp));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			System.out.println("Error. Problema al cargar los archivos de audio.");
		} catch (NullPointerException e) {
			;
		}
	}

	public void apertura() {
		if (activado) {
			try {
				apertura.setFramePosition(0);
				apertura.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio de la apertura.");
			}
		}

	}

	public void arregloPanel() {
		if (activado) {
			try {
				arregloPanel.setFramePosition(0);
				arregloPanel.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio del arreglo de panel.");
			}
		}

	}

	public void choqueLadrillo() {
		if (activado) {
			try {
				choqueLadrillo.setFramePosition(0);
				choqueLadrillo.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio del golpe a Felix.");
			}
		}

	}

	public void comerPastel() {
		if (activado) {
			try {
				comerPastel.setFramePosition(0);
				comerPastel.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio del pastel comido.");
			}
		}

	}

	public void inicioDeJuego() {
		if (activado) {
			try {
				inicioDeJuego.setFramePosition(0);
				inicioDeJuego.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio de inicio de juego.");
			}
		}

	}

	public void fondo(boolean bool) {
		if (bool) {
			try {
				fondo.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio de fondo de juego.");
			}
		} else {
			try {
				fondo.stop();
			} catch (NullPointerException e) {
				;
			}
		}
	}

	public void bloqueado() {
		if (activado) {
			try {
				bloqueado.setFramePosition(0);
				bloqueado.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio de movimiento inválido.");
			}
		}
	}

	public void levelUp() {
		if (activado) {
			try {
				levelUp.setFramePosition(0);
				levelUp.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio de pase de nivel.");
			}
		}

	}

	public void perdio() {
		if (activado) {
			try {
				perdio.setFramePosition(0);
				perdio.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio de game over.");
			}
		}

	}

	public void seccionUp() {
		if (activado) {
			try {
				seccionUp.setFramePosition(0);
				seccionUp.start();
			} catch (NullPointerException e) {
				System.out.println("No se pudo ejecutar el audio de pase de sección.");
			}
		}

	}
}
