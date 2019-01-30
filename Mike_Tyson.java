package Epsilon;
import javax.swing.JOptionPane;
public class Mike_Tyson extends Humano{
	public Mike_Tyson(){
		this("Mike Tyson",200,50);
	}
	public Mike_Tyson(String Nombre, int Salud, int Ataque){
		super(Nombre, Salud, Ataque);
	} 
	public void comunica(){}
	public void Automatico(int daño,int ataque){
		if(this.migatte_no_gokui_kogeki ){
			if(ataque>=0 || ataque<=3){
				this.BajaVida(daño);
			}
		}
		else if(ataque==0){
			this.BajaVida(daño);
		}
		else if(ataque==1 || ataque==2){
			this.estado="Defenza";
		}
	}
	public void muerte() {
			this.vivo=false;
			JOptionPane.showMessageDialog(null, "Felicidades Ganaste");
			
	}
	@Override
	public void CodigoTrampa(String codigo) {
		if(codigo.equals("MNGK")){ 
			this.migatte_no_gokui_kogeki=true;
		}
		if(codigo.equals("MNG")){
			this.migatte_no_gokui=true;
		}
		if(codigo.equals("BV")){
			this.vida=this.vida/2;
		}
		if(codigo.equals("BA")){
			this.Ataque=this.Ataque/2;
		}
		if(codigo.equals("Facil")){
			this.dificultad+=3;
		}
	}
	@Override
	public int calculaBarra() {
		this.largoBarra=(Salud*90)/200;
		return this.largoBarra;
	}
}