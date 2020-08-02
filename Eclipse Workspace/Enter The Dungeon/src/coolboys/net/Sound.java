package coolboys.net;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	static Clip clip;
	static long clipTimePosition;

	
	static void playSound(String soundLocation){
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

}