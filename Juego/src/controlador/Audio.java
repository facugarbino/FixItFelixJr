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

		URL urlInicio = getClass().getResource("/recursos/audio/musicaApertura.wav");
		try {
			musicaApertura.open(AudioSystem.getAudioInputStream(urlInicio));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void musicaApertura() {
		musicaApertura.start();
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