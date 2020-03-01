package model.logic;

public class InfraccionCantidad implements Comparable<InfraccionCantidad>{

	private String infraccion;
	
	private int n;
	
	public InfraccionCantidad(String b, int a)
	{
		infraccion=b;
		n=a;
	}

	@Override
	public int compareTo(InfraccionCantidad o) {
		int retorno=0;
		
		if(n>o.getN()){retorno=1;}
		else if(n<o.getN()){retorno=-1;}
		
		return retorno;
	}

	public String getInfraccion() {
		return infraccion;
	}

	public void setInfraccion(String infraccion) {
		this.infraccion = infraccion;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	public String toString()
	{
		return infraccion +"        | "+n;
		
	}
	
}
