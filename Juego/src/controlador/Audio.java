package controlador;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
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

	public static Audio getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		}
		return (INSTANCE = new Audio());
	}

	public Audio() {
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
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
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
		apertura.setFramePosition(0);
		apertura.start();
	}

	public void arregloPanel() {
		arregloPanel.setFramePosition(0);
		arregloPanel.start();
	}

	public void choqueLadrillo() {
		choqueLadrillo.setFramePosition(0);
		choqueLadrillo.start();
	}

	public void comerPastel() {
		comerPastel.setFramePosition(0);
		comerPastel.start();
	}

	public void inicioDeJuego() {
		inicioDeJuego.setFramePosition(0);
		inicioDeJuego.start();
	}

	public void fondo() {
		fondo.setFramePosition(0);
		fondo.start();
	}

	public void bloqueado() {
		bloqueado.setFramePosition(0);
		bloqueado.start();
	}

	public void levelUp() {
		levelUp.setFramePosition(0);
		levelUp.start();
	}

	public void perdio() {
		perdio.setFramePosition(0);
		perdio.start();
	}

	public void seccionUp() {
		seccionUp.setFramePosition(0);
		seccionUp.start();
	}
}
