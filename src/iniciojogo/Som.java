package iniciojogo;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


public class Som {

	boolean playCompleted;

	public static synchronized void play(int fileName) {

		final int MUSIC_N = 3;
		final int BACK_GROUNG = 0;
		final int GAMEOVER = 1;


		String[] address;
		address = new String[MUSIC_N];

		address[BACK_GROUNG] = "music/game.wav";
		address[GAMEOVER] = "music/gameOver.wav";

		new Thread(new Runnable() {
			public void run() {
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(address[fileName]));
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
