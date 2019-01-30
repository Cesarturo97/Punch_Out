package Epsilon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
public class GuardaDatos {
	String[] valores= new String[2];
	public String guardaDatos(){
		try{
			BufferedReader fr=new BufferedReader(new FileReader("puntuacion.txt"));
			this.valores[0]=fr.readLine();
			this.valores[1]=fr.readLine();
			fr.close();
		}catch(Exception ex){
			System.out.println("Error al leer datos");
		}
		return this.valores[1];
	} 
	public void nuevaPuntuacionAlta(int puntuacion){
		try{
		valores[0]=JOptionPane.showInputDialog("Ingresa tu nombre \n ganador");
		valores[1]=""+puntuacion+"";
		PrintWriter pw=new PrintWriter(new FileWriter("puntuacion.txt"));
			pw.println(valores[0]);
			pw.println(valores[1]);
		pw.close();
		}catch(Exception ex){
				System.out.println("Error al guardar datos");
		}
	}
}
