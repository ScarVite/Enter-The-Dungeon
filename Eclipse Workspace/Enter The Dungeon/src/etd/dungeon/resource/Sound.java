package etd.dungeon.resource;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private Clip clip;

	//Hauptfunktion für das einlesen und abspielen von Dateien
	public void playSound(String soundLocation){
		try{
			File soundPath = new File(soundLocation);
				AudioInputStream soundInput = AudioSystem.getAudioInputStream(soundPath);
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

}
