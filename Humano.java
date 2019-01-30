package Epsilon;
import java.util.Random;
public abstract class Humano {
	protected int Salud, Ataque, aleatorio, vida,largoBarra,dificultad=3;
	protected String nombre, estado="Bailar";
	protected boolean vivo=true, 
						migatte_no_gokui=false,
						migatte_no_gokui_kogeki=false, migatte_no_gokui_kogeki_perfecto=false;
	public Humano(){
		this("",100,5);
	}
	public Humano(String nombre,int Salud, int Ataque){
		this.Salud=Salud;
		this.Ataque=Ataque;
		this.nombre=nombre;
	}
	public void BajaVida(int daño){
		this.Salud-=daño;
		if (this.Salud<=0){
			this.muerte();
		}
	}
	public int DefineAleatorio(){
		Random ran=new Random();
		this.aleatorio=ran.nextInt(this.dificultad);
		return this.aleatorio;
	}
	public abstract int calculaBarra();
	public abstract void muerte();
	public abstract void CodigoTrampa(String codigo);
	public String toString(){
		return "\n"+"Nombre: "+this.nombre+"\n"+"Salud: "+this.Salud+" \n"+"Ataque: "+this.Ataque;
	}
}
