package Epsilon;
import java.io.File;
import javax.sound.sampled.*;
public class Musica extends Thread{
	private String tipoMusica;
	File fight, 
		mng;
		//title;
	public Musica(){
		this("musica1");
	}
	public Musica(String tipoMusica){
		this.tipoMusica=tipoMusica;
		//title=new File("title.wav").getAbsoluteFile();
		mng=new File("Ultimate Battle.wav").getAbsoluteFile();
		fight=new File("Fight Theme.wav").getAbsoluteFile();
	}
	public void setTipoMusica(String tipoMusica){
			this.tipoMusica=tipoMusica;
			
		}
	public void run(){
		if (this.tipoMusica=="musica2"){
			this.Battle(mng);
		}
		if (this.tipoMusica=="musica1"){
			this.Battle(fight);
		}
	}
	public void Battle(File musica) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musica);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			while(!clip.isRunning()){
				Thread.sleep(10);
			}
			while(clip.isRunning()){
				Thread.sleep(10);
			}
			clip.close();
		}catch(Exception ex) {
			System.out.println("Error al reproducir el audio.");
			ex.printStackTrace();
		}
	}
}
