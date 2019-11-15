package controlador;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
	private boolean fondoOn;
	private static Audio INSTANCE = null;
	private static boolean activado = true;

	public static void setActivado() {
		Audio.activado = !activado;
	}

	public static boolean getActivado() {
		return activado;
	}

	public static Audio getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}
		return (INSTANCE = new Audio());
	}

	private Audio() {
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
		} catch (Exception e1) {
			System.out.println("Error. No se podrá jugar con audio");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apertura() {
		if (activado) {
			try {
				apertura.setFramePosition(0);
				apertura.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void arregloPanel() {
		if (activado) {
			try {
				arregloPanel.setFramePosition(0);
				arregloPanel.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void choqueLadrillo() {
		if (activado) {
			try {
				choqueLadrillo.setFramePosition(0);
				choqueLadrillo.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void comerPastel() {
		if (activado) {
			try {
				comerPastel.setFramePosition(0);
				comerPastel.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void inicioDeJuego() {
		if (activado) {
			try {
				inicioDeJuego.setFramePosition(0);
				inicioDeJuego.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void fondo(boolean bool) {
		if (bool) {
			try {
				fondo.loop(Clip.LOOP_CONTINUOUSLY);
				fondoOn = true;
//				fondo.setFramePosition(0);
//				fondo.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				fondo.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void bloqueado() {
		if (activado) {
			try {

				bloqueado.setFramePosition(0);
				bloqueado.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void levelUp() {
		if (activado) {
			try {
				levelUp.setFramePosition(0);
				levelUp.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void perdio() {
		if (activado) {
			try {
				perdio.setFramePosition(0);
				perdio.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void seccionUp() {
		if (activado) {
			try {
				seccionUp.setFramePosition(0);
				seccionUp.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
