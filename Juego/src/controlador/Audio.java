package controlador;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Audio {

	private Clip musicaApertura;
	private Clip arregloPanel;
	private Clip choqueLadrillo;
	private Clip comerPastel;
	private Clip inicioDeJuego;
	private Clip musicaFondo;
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
			musicaApertura = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}

		URL urlMusicaApertura = getClass().getResource("/recursos/audio/musicaApertura.wav");
		URL urlArregloPanel = getClass().getResource("/recursos/audio/arregloPanel.wav");
		URL urlChoqueLadrillo = getClass().getResource("/recursos/audio/choqueLadrillo.wav");
		URL urlComerPastel = getClass().getResource("/recursos/audio/comerPastel.wav");
		URL urlInicioDeJuego = getClass().getResource("/recursos/audio/inicioDeJuego.wav");
		URL urlMusicaFondo = getClass().getResource("/recursos/audio/musicaFondo.wav");
		URL urlBLoqueado = getClass().getResource("/recursos/audio/bloqueado.wav");
		URL urlLevelUp = getClass().getResource("/recursos/audio/levelUp.wav");
		URL urlPerdio = getClass().getResource("/recursos/audio/perdio.wav");
		URL urlSeccionUp = getClass().getResource("/recursos/audio/seccionUp.wav");
		
		try {
			musicaApertura.open(AudioSystem.getAudioInputStream(urlMusicaApertura));
			choqueLadrillo.open(AudioSystem.getAudioInputStream(urlChoqueLadrillo));
			comerPastel.open(AudioSystem.getAudioInputStream(urlComerPastel));
			inicioDeJuego.open(AudioSystem.getAudioInputStream(urlInicioDeJuego));
			arregloPanel.open(AudioSystem.getAudioInputStream(urlArregloPanel));
			musicaFondo.open(AudioSystem.getAudioInputStream(urlMusicaFondo));
			bloqueado.open(AudioSystem.getAudioInputStream(urlBLoqueado));
			levelUp.open(AudioSystem.getAudioInputStream(urlLevelUp));
			perdio.open(AudioSystem.getAudioInputStream(urlPerdio));
			seccionUp.open(AudioSystem.getAudioInputStream(urlSeccionUp));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void musicaApertura() {
		musicaApertura.start();
	}
	
	public void musicaArregloPanel() {
		arregloPanel.start();
	}
	
	public void musicaChoqueLadrillo() {
		choqueLadrillo.start();
	}
	
	public void musicaComerPastel() {
		comerPastel.start();
	}
	
	public void musicaInicioDeJuego() {
		inicioDeJuego.start();
	}
	
	public void musicaDeFondo() {
		musicaFondo.start();
	}
	
	public void musicaBLoqueado() {
		bloqueado.start();
	}
	
	public void musicaLevelUp() {
		levelUp.start();
	}
	
	public void musicaPerdio() {
		perdio.start();
	}
	
	public void musicaSeccionUp() {
		seccionUp.start();
	}

	
	/*
	public void MusicaSuperOn() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		musicaSuper.setFramePosition(0);
		musicaSuper.start();
	}
	public void MusicaSuperOff(){
		musicaSuper.stop();
	}
	public void MusicaMurioOn(){
		murioPacman.setFramePosition(0);
		murioPacman.start();
		murioPacman.loop(0);
	}
	public void MusicaComioOn(){
		comioFantasma.setFramePosition(0);
		comioFantasma.start();
		comioFantasma.loop(0);
	}
	*/
}