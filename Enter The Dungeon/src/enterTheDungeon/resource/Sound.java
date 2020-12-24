package enterTheDungeon.resource;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private Clip clip;
	private static boolean hintergrundmusik = true;

	//Hauptfunktion für das einlesen und abspielen von Dateien
	public void playSound(File soundFile){
		try{
				AudioInputStream soundInput = AudioSystem.getAudioInputStream(soundFile);
			    clip = AudioSystem.getClip();
				clip.open(soundInput);
				clip.start();
			}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public boolean getHintergrundmusik() {
		return hintergrundmusik;
	}

	public void setHintergrundmusik(boolean hintergrundmusikBool) {
		hintergrundmusik = hintergrundmusikBool;
	}


}
