package model.logic;

public class Fecha implements Comparable<Fecha>{

	private int ano;
	
	private int mes;
	
	private int dia;
	
	public Fecha(int d, int m,int a)
	{
		ano=a;
		mes=m;
		dia=d;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int compareTo(Fecha o) {
		int retorno=0;
		
		if(mes>o.getMes()){ retorno=1;}
		else if(mes<o.getMes()) {retorno=-1;}
		else if(dia>o.getDia()) {retorno=1;}
		else if(dia<o.getDia()) {retorno=-1;}
		
		return retorno;
	}
	
	
	
	
	
}
