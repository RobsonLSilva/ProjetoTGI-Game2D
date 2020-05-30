package iniciojogo;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


public class Som {

	boolean playCompleted;

	public static synchronized void play() {

		final String music;

		music = "music/game.wav";

		new Thread(new Runnable() {
			public void run() {
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(music));
					AudioFormat format = audioStream.getFormat();
					DataLine.Info info = new DataLine.Info(Clip.class, format);
					Clip audioClip = (Clip) AudioSystem.getLine(info);
					audioClip.open(audioStream);
					audioClip.start();
					audioClip.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (Exception e) {
					
				}
			}
		}).start();

	}
	

}
