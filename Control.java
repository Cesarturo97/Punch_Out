package Epsilon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Control extends JPanel implements KeyListener,Runnable{
	Musica musica=new Musica();
	GuardaDatos guardar=new GuardaDatos();
	private int cambiadorRival=0, 
				cambiadorJugador=0,
				movimientoJugador=0,
				cambioespecial=0,
				rangoTiempo=0,
				movimientoRival=0;
	private boolean portada=false;
	private Font pixel;
	Little_Mac jugador;
	Mike_Tyson rival;
	private Image fondo,
					Title,
					jugadorEsquive,
					jugadorBloqueo,
					jugadorPunch,
					jugadorEsquiveMNG,
					jugadorBloqueoMNG,
					jugadorPunchMNG, reflejo;
	private Image[] rivalAtack,
					rivalDance,
					rivalNormal,
					rivalDefenza,
					rivalMuerte,
					//------------------------
					jugadorDance,
					jugadorCargado,
					jugadorDanceMNG,
					jugadorCargadoMNG; 
	Thread hilo=new Thread(this);
	public Control(){
		super();
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(500,500));
		this.jugador=new Little_Mac();
		this.rival=new Mike_Tyson();
		this.rivalAtack=new Image[4];
		this.rivalDance=new Image[4];
		this.rivalNormal=new Image[3];
		this.rivalDefenza=new Image[2];
		this.rivalMuerte=new Image[3];
		this.jugadorDance=new Image[2];
		this.jugadorCargado=new Image[4];
		this.jugadorDanceMNG=new Image[2];
		this.jugadorCargadoMNG=new Image[4];
		this.pixel=new Font("LUCIDA CONSOLE", Font.BOLD, 20);
		this.Title=new ImageIcon("title.png").getImage();
		this.fondo=new ImageIcon("Ring Beta.png").getImage();
		this.reflejo=new ImageIcon("Reflejo.png").getImage();
		this.rivalAtack[0]=new ImageIcon("MTA1.jpg").getImage();
		this.rivalAtack[1]=new ImageIcon("MTA2.jpg").getImage();
		this.rivalAtack[2]=new ImageIcon("MTA3.jpg").getImage();
		this.rivalAtack[3]=new ImageIcon("MTA4.jpg").getImage();
		this.rivalDance[0]=new ImageIcon("MTD1.jpg").getImage();
		this.rivalDance[1]=new ImageIcon("MTD2.jpg").getImage();
		this.rivalDance[2]=new ImageIcon("MTD3.jpg").getImage();
		this.rivalDance[3]=new ImageIcon("MTD4.jpg").getImage();
		this.rivalNormal[0]=new ImageIcon("MTN1.jpg").getImage();
		this.rivalNormal[1]=new ImageIcon("MTN2.jpg").getImage();
		this.rivalDefenza[0]=new ImageIcon("MTD.jpg").getImage();
		this.rivalDefenza[1]=new ImageIcon("MTD.jpg").getImage();
		this.rivalMuerte[0]=new ImageIcon("Death1.jpg").getImage();
		this.rivalMuerte[1]=new ImageIcon("Death2.jpg").getImage();
		this.rivalMuerte[2]=new ImageIcon("Death3.jpg").getImage();
		//-------------------------------------------------------
		this.jugadorEsquive=new ImageIcon("LME3.png").getImage();
		this.jugadorBloqueo=new ImageIcon("LMB2.png").getImage();
		this.jugadorPunch=new ImageIcon("LMP3.png").getImage();
		this.jugadorDance[0]=new ImageIcon("LMD1.png").getImage();
		this.jugadorDance[1]=new ImageIcon("LMD2.png").getImage();
		this.jugadorCargado[0]=new ImageIcon("LMC1.png").getImage();
		this.jugadorCargado[1]=new ImageIcon("LMC2.png").getImage();
		this.jugadorCargado[2]=new ImageIcon("LMC3.png").getImage();
		this.jugadorCargado[3]=new ImageIcon("LMC4.png").getImage();
		//------------------------------------------------------------
		this.jugadorEsquiveMNG=new ImageIcon("LME2MNG.png").getImage();
		this.jugadorBloqueoMNG=new ImageIcon("LMB2MNG.png").getImage();
		this.jugadorPunchMNG=new ImageIcon("LMP3MNG.png").getImage();
		this.jugadorDanceMNG[0]=new ImageIcon("LMD1MNG.png").getImage();
		this.jugadorDanceMNG[1]=new ImageIcon("LMD2MNG.png").getImage();
		this.jugadorCargadoMNG[0]=new ImageIcon("LMC1MNG.png").getImage();
		this.jugadorCargadoMNG[1]=new ImageIcon("LMC2MNG.png").getImage();
		this.jugadorCargadoMNG[2]=new ImageIcon("LMC3MNG.png").getImage();
		this.jugadorCargadoMNG[3]=new ImageIcon("LMC4MNG.png").getImage();
	}
	public void getCodigo(){
		String Codigo;
		try{
			Codigo=JOptionPane.showInputDialog("Escribe codigo de trampa");
			this.jugador.CodigoTrampa(Codigo);
			this.rival.CodigoTrampa(Codigo);
		}catch(Exception e){
			Codigo="";
		}
	}
	public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if(!this.portada){
    		g.drawImage(this.Title, 0, 0,this.getWidth(),this.getHeight(), this);
    		repaint();
    	}else{
	    	g.drawImage(this.fondo, 0, 0,this.getWidth(),this.getHeight(), this);
	    	g.setColor(Color.WHITE);
	    	g.setFont(this.pixel);
	    	g.drawString(""+this.jugador.numEpeciales, 63, 53);
	    	g.drawString(""+this.jugador.puntaje, 360, 75);
	    	g.setColor(Color.GREEN);
	    	g.fillRect(178, 42, this.jugador.calculaBarra(),11);
	    	g.setColor(Color.RED);
	    	g.fillRect(288, 42, this.rival.calculaBarra(),11);
	    	g.drawString(""+this.jugador.numBloqueo, 120, 53);
	    	if(rival.vivo)
	    	{
	    		if(this.rival.estado=="Ataque"){
		    		g.drawImage(this.rivalAtack[this.cambiadorRival], 200, 200+this.movimientoRival, 100,120, this);
		    	}else if(this.rival.estado=="Normal"){
		    		g.drawImage(this.rivalNormal[this.cambiadorRival], 200, 200+this.movimientoRival, 100,120, this);
		    	}else if(this.rival.estado=="Defenza"){
		    		g.drawImage(this.rivalDefenza[0], 200, 200, 100,120, this);
		    	}else{
		    		g.drawImage(this.rivalDance[this.cambiadorRival], 200, 200, 100,120, this);
		    	}
	    	}else{
	    		g.drawImage(this.rivalMuerte[this.cambiadorRival], 200, 200-this.movimientoRival, 100, 120, this);
	    	}
	    	//--------------------------------------------------------------------------------------
	    	if(this.jugador.migatte_no_gokui || this.jugador.migatte_no_gokui_kogeki || this.jugador.migatte_no_gokui_kogeki_perfecto){
		    	if(this.jugador.estado=="Esquive"){
		    		g.drawImage(this.jugadorEsquiveMNG, 220, 330, 80, 85, this);
		    	}else if(this.jugador.estado=="Bloqueo"){
		    		if(this.jugador.contra){
		    			g.drawImage(this.reflejo, 210, 320, 40, 40, this);
		    		}
		    			g.drawImage(this.jugadorBloqueoMNG, 200,300,85, 110, this);
		    	}else if(this.jugador.estado=="Golpe"){
		    		g.drawImage(this.jugadorPunchMNG, 190, 280, 120, 120, this);
		    	}else if(this.jugador.estado=="Cargado"){
		    		g.drawImage(this.jugadorCargadoMNG[this.cambioespecial], 200, 270, 120, 120, this);   
		    	}else {
		    		g.drawImage(this.jugadorDanceMNG[this.cambiadorJugador], 210, 300, 80, 120, this);
		    	}
	    	}else{
	    		if(this.jugador.estado=="Esquive"){
		    		g.drawImage(this.jugadorEsquive, 220, 330, 85, 100, this);
		    	}else if(this.jugador.estado=="Bloqueo"){
		    		g.drawImage(this.jugadorBloqueo, 200,300,85, 110, this);
		    	}else if(this.jugador.estado=="Golpe"){
		    		g.drawImage(this.jugadorPunch, 190, 280, 120, 120, this);
		    	}else if(this.jugador.estado=="Cargado"){
		    		g.drawImage(this.jugadorCargado[this.cambioespecial], 200, 270, 120, 120, this);   
		    	}else {
		    		g.drawImage(this.jugadorDance[this.cambiadorJugador], 210, 300, 80, 120, this);
		    	}
	    	}
	    	repaint();
    	}
    }
	@Override
	public void run() {
		try{
			while(this.rival.vivo && this.jugador.vivo){
				if(this.rival.DefineAleatorio()==1){
					this.rival.estado="Ataque";
					this.rangoTiempo=rivalAtack.length;
				}
				if(this.rival.DefineAleatorio()==0){
					this.rival.estado="Normal";
					this.rangoTiempo=2;
				}
				for(int i=0;i<=this.rangoTiempo;i++){
					if(i<this.rangoTiempo){
						this.cambiadorRival=i;
						this.movimientoRival+=10;
					}
					if(this.jugador.estado=="Baile"){
						movimientoJugador++;
						if(movimientoJugador==2){
							movimientoJugador=0;
						}
					}
					if(this.jugador.estado=="Cargado" && this.jugador.numEpeciales>0){
						for(int j=0;j<=3;j++){
							this.cambioespecial=j;
							Thread.sleep(90);
							if(j==2){
								this.rival.Automatico(this.jugador.Ataque*3, this.jugador.DefineAleatorio());
								if(this.rival.estado=="bloqueo"){
									this.jugador.puntaje++;
								}else{
									this.jugador.puntaje+=6;
								}
							}
							if(j==3){
								this.jugador.estado="Baile";
							}
						}
						this.cambioespecial=0;
						this.jugador.numEpeciales--;
					}else if(this.jugador.estado=="Cargado" && this.jugador.numEpeciales<=0){
						this.jugador.estado="Ataque";
					}
					this.cambiadorJugador=movimientoJugador;
					if(this.rival.estado=="Ataque" && i==3){
						this.jugador.Automatico(this.rival.Ataque);
					}
					if(this.rival.estado=="Normal" && i==1){
						this.jugador.Automatico(this.rival.Ataque/2);
					}
					Thread.sleep(140);
				}
				this.rival.estado="Bailar";
				this.movimientoRival=0;
				if(this.jugador.migatte_no_gokui_kogeki_perfecto){
					this.jugador.estado="Golpe";
					this.rival.Automatico(this.jugador.Ataque*2, this.jugador.DefineAleatorio());
					Thread.sleep(30);
				}
				if(this.jugador.migatte_no_gokui || this.jugador.migatte_no_gokui_kogeki || this.jugador.migatte_no_gokui_kogeki_perfecto){
					this.jugador.estado="Baile";
				}
			}
			this.rangoTiempo=2;
			this.cambiadorRival=0;
			for(int i=0;i<=this.rangoTiempo;i++){
				this.cambiadorRival=i;
				if(i==0){
					Thread.sleep(100);
				}
				Thread.sleep(200);
			}
			if(!this.jugador.vivo || !this.rival.vivo){
				JOptionPane.showMessageDialog(null, "Se acabo");
				if(Integer.parseInt(this.guardar.guardaDatos())<this.jugador.puntaje){
					this.guardar.nuevaPuntuacionAlta(this.jugador.puntaje);
				}	
				System.exit(0);
			}
		}catch (InterruptedException e){
			System.out.println();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			this.jugador.estado="Golpe";
			this.jugador.puntaje++;
			this.rival.Automatico(this.jugador.Ataque, this.jugador.DefineAleatorio());
		}
		if(key == KeyEvent.VK_DOWN) {
			this.jugador.estado="Bloqueo";
		}
		if(key == KeyEvent.VK_LEFT) {
			this.jugador.estado="Esquive";
		}
		if(key == KeyEvent.VK_RIGHT) {
			this.jugador.estado="Cargado";
		}
		if(key == KeyEvent.VK_ENTER){
			if(!this.portada){
				this.portada=true;
				if(this.jugador.migatte_no_gokui || this.jugador.migatte_no_gokui_kogeki){
					this.musica.setTipoMusica("musica2");
				} else{
					this.musica.setTipoMusica("musica1");
				}
				musica.start();
				hilo.start();
			}
		}
		if(key == KeyEvent.VK_C){
			this.getCodigo();
		}
		if(this.jugador.migatte_no_gokui || this.jugador.migatte_no_gokui_kogeki){
			if(key == KeyEvent.VK_O){
				this.jugador.migatte_no_gokui=false;
				this.jugador.migatte_no_gokui_kogeki=false;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_DOWN) {
			this.jugador.estado="Baile";
		}
		if(key == KeyEvent.VK_LEFT) {
			this.jugador.estado="Baile";
		}
		if(key == KeyEvent.VK_RIGHT) {
			this.jugador.estado="Cargado";
		}
		if(key == KeyEvent.VK_UP) {
			this.jugador.estado="Baile";
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
