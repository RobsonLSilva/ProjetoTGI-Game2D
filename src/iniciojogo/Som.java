package iniciojogo;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Som implements LineListener {

	boolean playCompleted;

	private File audioFile;
	private Clip audioClip;
	
	String audioFilePath = "src/res/game.wav";
	
	public void play() {

		audioFile = new File(audioFilePath);
		
		try {
			
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.addLineListener(this);
			audioClip.open(audioStream);
			audioClip.start();
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);

			while (!playCompleted) {
				// wait for the playback completes
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			audioClip.close();

		} catch (UnsupportedAudioFileException ex) {
			System.out.println("O arquivo de áudio especificado não é suportado");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.out.println("A linha de áudio para reprodução não está disponível.");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Erro ao reproduzir o arquivo de áudio.");
			ex.printStackTrace();
		}

	}
	
	public void stop() {
		
		audioClip.stop();
	}
	
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();

		if (type == LineEvent.Type.STOP) {
			playCompleted = true;
			
		}

	}
	
}
