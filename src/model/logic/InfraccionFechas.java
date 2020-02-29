package model.logic;

public class InfraccionFechas implements Comparable<InfraccionFechas>{

	
	private String infra;
	
	private int fechainicial;
	
	private int fechafinal;
	
	
	public InfraccionFechas(String infraccion, int i, int j)
	{
		infra=infraccion;
		fechainicial=i;
		fechafinal=j;
	}


	public String getInfra() {
		return infra;
	}


	public void setInfra(String infra) {
		this.infra = infra;
	}


	public int getFechainicial() {
		return fechainicial;
	}


	public void setFechainicial(int fechainicial) {
		this.fechainicial = fechainicial;
	}


	public int getFechafinal() {
		return fechafinal;
	}


	public void setFechafinal(int fechafinal) {
		this.fechafinal = fechafinal;
	}

	public String toString()
	{
		return (infra+"        | "+fechainicial+"          | "+fechafinal);
	}
	
	@Override
	public int compareTo(InfraccionFechas infrf) {
		// TODO Auto-generated method stub
		String realinfra=infra;
		String infra= infrf.getInfra();

		
		int retorno=0;
		
		if(realinfra.compareTo(infra)<0){ retorno=-1;}
		else if(realinfra.compareTo(infra)>0){ retorno=1;}
		
		return retorno;
	}
	
	
	
}
