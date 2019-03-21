import java.awt.BorderLayout;

import javax.swing.JPanel;

public class escalerasYSer {
	private JPanel gui = new JPanel(new BorderLayout(30, 30));
	String Entrada;
	String Salida;
	int NUmEntrada;
	int NumSalida;
	String Nombre;
	escalerasYSer(String pSEntrada,String pSSalida,String pNombre,int pIEntrada,int pISalida){
		Nombre=pNombre;
		Entrada=pSEntrada;
		Salida=pSSalida;
		NUmEntrada=pIEntrada;
		NumSalida=pISalida;
		
	}
	public String getNombre() {
		return Nombre;
	}
	public String getEntrada() {
		return Entrada;
	}
	public String getSalida() {
		return Salida;
	}
	
	public int getNUmEntrada() {
		return NUmEntrada;
	}
	public int getNumSalida() {
		return NumSalida;
	}
	public void setEntrada(String entrada) {
		Entrada = entrada;
	}
	public void setNombre(String pNombre) {
		Nombre = pNombre;
	}
	public void setSalida(String salida) {
		Salida = salida;
	}
	public void setNUmEntrada(int nUmEntrada) {
		NUmEntrada = nUmEntrada;
	}
	public void setNumSalida(int numSalida) {
		NumSalida = numSalida;
	}
	

}
