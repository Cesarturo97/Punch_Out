package Epsilon;
import javax.swing.JOptionPane;

public class Little_Mac extends Humano{
	public boolean bloqueo, esquive,ataque,cargado,contra;
	public int numBloqueo=4,numEpeciales=3,puntaje=0;
	public Little_Mac(){
		this("Little Mac",100,5);
	}
	public Little_Mac(String Nombre, int Salud, int Ataque){
		super(Nombre,Salud, Ataque);
	}
	@Override
	public void muerte() {
			this.vivo=false;
	}
	public void Automatico(int daño){
		if(migatte_no_gokui_kogeki_perfecto){
			this.Ataque=10;
			this.estado="Esquive";
		}else if(migatte_no_gokui_kogeki){
			this.Ataque=3;
			this.estado="Esquive";
		}else if(migatte_no_gokui){
			this.estado="Esquive";
		}else if(!migatte_no_gokui){
			if(this.estado=="Bloqueo"){
				this.contra=true;
				this.numBloqueo--;
				this.puntaje+=5;
				if(this.numBloqueo<=0){
					this.BajaVida(daño);
				}
				this.contra=false;
			}else if(this.estado=="Esquive"){
				
			}else{
				this.BajaVida(daño);
			}
		}
	}
	@Override
	public void CodigoTrampa(String codigo) {
		if(codigo.equals("MNGK")){
			this.migatte_no_gokui_kogeki=true;
			JOptionPane.showMessageDialog(null,"Migatte no gokui kogeki activado");
		}
		if(codigo.equals("MNG")){
			this.migatte_no_gokui=true;
			JOptionPane.showMessageDialog(null,"Migatte no gokui activado");
		}
		if(codigo.equals("SV")){
			this.Salud=this.Salud*4;
			JOptionPane.showMessageDialog(null,"Salud x4");
		}
		if(codigo.equals("SA")){
			this.Ataque=this.Ataque*4;
			JOptionPane.showMessageDialog(null,"Ataque x4");
		}
		if(codigo.equals("ESTEROIDES")){
			this.Ataque=this.Ataque*6;
			this.vida=this.vida*4;
			JOptionPane.showMessageDialog(null,"En ESTEROIDES \n Ataque x4 Salud X4");
		}
		if(codigo.equals("MNB")){
			this.numBloqueo++;
			JOptionPane.showMessageDialog(null,"Numero de bloqueos +1");
		}
		if(codigo.equals("MNE")){
			this.numEpeciales=this.numEpeciales*2;
			JOptionPane.showMessageDialog(null,"Numero de especiales *2");
		}
		if(codigo.equals("MNGP")){
			migatte_no_gokui_kogeki_perfecto=true;
			JOptionPane.showMessageDialog(null,"Migatte no gokui kogeki perfecto activado");
		}
	}
	@Override
	public int calculaBarra() {
		this.largoBarra=(Salud*90)/100;
		return this.largoBarra;
	}
}
