package enterTheDungeon.resource;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private Clip clip;
	private static boolean hintergrundmusik = true;

	//Hauptfunktion für das einlesen und abspielen von Dateien

	public void playSound(String soundLocation){
		try{
			// In soundPath kann jetzt immer der Pfad vom Sound gespeichert werden
				File soundPath = new File(soundLocation);
				//Der Pfad wird reingeladen und dann abgespielt
				AudioInputStream soundInput = AudioSystem.getAudioInputStream(soundPath);
			    clip = AudioSystem.getClip();
				clip.open(soundInput);
				clip.start();
			}
		catch (Exception e){
			e.printStackTrace();
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
