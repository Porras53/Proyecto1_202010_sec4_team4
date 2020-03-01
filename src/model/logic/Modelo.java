package model.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import model.data_structures.*;
import sun.misc.IOUtils;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	
	/**
	 * Cola de lista encadenada.
	 */
	private ListaDoblementeEncadenada datosCola;

	private double[] zonaminiMax;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datosCola = new ListaDoblementeEncadenada();
	}

	/**
	 * Carga el archivo .JSON en una lista enlazada.
	 * @throws FileNotFoundException. Si no encuentra el archivo.
	 */

	public void cargar() throws FileNotFoundException
	{
		//Definir mejor la entrada para el lector de json
		long inicio = System.currentTimeMillis();
		long inicio2 = System.nanoTime();
		String dir= "./data/comparendos_dei_2018.geojson";
		File archivo= new File(dir);
		JsonReader reader= new JsonReader( new InputStreamReader(new FileInputStream(archivo)));
		JsonObject gsonObj0= JsonParser.parseReader(reader).getAsJsonObject();

		JsonArray comparendos=gsonObj0.get("features").getAsJsonArray();
		
		double menorlatitud=90;
		double mayorlatitud=-90;
		double menorlongitud=180;
		double mayorlongitud=-180;
		int i=0;
		while(i<comparendos.size())
		{
			JsonElement obj= comparendos.get(i);
			JsonObject gsonObj= obj.getAsJsonObject();
			JsonObject gsonObjpropiedades=gsonObj.get("properties").getAsJsonObject();
			int objid= gsonObjpropiedades.get("OBJECTID").getAsInt();
			String fecha= gsonObjpropiedades.get("FECHA_HORA").getAsString();
			String clasevehiculo=gsonObjpropiedades.get("CLASE_VEHI").getAsString();
			String tiposervi=gsonObjpropiedades.get("TIPO_SERVI").getAsString();
			String infraccion=gsonObjpropiedades.get("INFRACCION").getAsString();
			String desinfraccion=gsonObjpropiedades.get("DES_INFRAC").getAsString();
			String localidad=gsonObjpropiedades.get("LOCALIDAD").getAsString();

			JsonObject gsonObjgeometria=gsonObj.get("geometry").getAsJsonObject();

			JsonArray gsonArrcoordenadas= gsonObjgeometria.get("coordinates").getAsJsonArray();
			double longitud= gsonArrcoordenadas.get(0).getAsDouble();
			double latitud= gsonArrcoordenadas.get(1).getAsDouble();
			
			if(longitud>mayorlongitud)
			{
				mayorlongitud=longitud;
				
			}
			else if(longitud<menorlongitud)
			{
				menorlongitud=longitud;
			}
			
			if(latitud>mayorlatitud)
			{
				mayorlatitud=latitud;
				
			}
			else if(latitud<menorlatitud)
			{
				menorlatitud=latitud;
			}
			
			

			Comparendo agregar=new Comparendo(objid, fecha, clasevehiculo, tiposervi, infraccion, desinfraccion, localidad, longitud,latitud);
			datosCola.insertarFinal(agregar);
			i++;
		}
		long fin2 = System.nanoTime();
		long fin = System.currentTimeMillis();
		
		zonaminiMax= new double[4];
		zonaminiMax[0]=menorlatitud;
		zonaminiMax[1]=menorlongitud;
		zonaminiMax[2]=mayorlatitud;
		zonaminiMax[3]=mayorlongitud;
		

		double tiempo = (double) ((fin - inicio)/1000);
		System.out.println((fin2-inicio2)/1.0e9 +" segundos");
		System.out.println(tiempo +" segundos");


	}

	
	

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */

	public ListaDoblementeEncadenada getDatosListaEncadenada() {
		return datosCola;
	}

	public double[] getZonaminiMax() {
		return zonaminiMax;
	}


	public Comparendo buscarPrimerComparendoLocalidad(String localidad)
	{
		Node puntero= datosCola.darCabeza2();
		int i=0;
		boolean encontrado=false;
		Comparendo retorno= null;
		while(i<datosCola.darLongitud() && !encontrado)
		{
			if(((Comparendo)puntero.darE()).getLocalidad().equalsIgnoreCase(localidad))
			{
				retorno= ((Comparendo)puntero.darE());
				encontrado=true;
				
			}
			i++;
			puntero=puntero.darSiguiente();
		}
		
		return retorno;
	}
	
	public Comparendo buscarPrimerComparendoInfraccion(String codigoinfraccion)
	{
		Comparendo resp =null;
		ListaDoblementeEncadenada<Comparendo> colaAuxiliar= datosCola;
		int length = colaAuxiliar.darLongitud();
		for(int i=0; i< length && resp==null; i++)
		{
			Comparendo temp=  colaAuxiliar.eliminarComienzo();
			
			if(codigoinfraccion.compareTo(temp.getInfraccion())==0)
			{
				resp=temp;

			}
		}
		return resp;
	}
	
	public ListaDoblementeEncadenada<Comparendo> buscarComparendosPorFecha(String fecha)
	{
		Node puntero= datosCola.darCabeza2();
		int i=0;
		ListaDoblementeEncadenada<Comparendo> nueva= new ListaDoblementeEncadenada<Comparendo>();
		while(i<datosCola.darLongitud())
		{
			if(((Comparendo)puntero.darE()).getFecha().equalsIgnoreCase(fecha))
			{
				nueva.insertarFinal(((Comparendo)puntero.darE()));
				
			}
			i++;
			puntero=puntero.darSiguiente();
		}
		
		Comparable[] nuevo= copiarComparendos(nueva,0);
		shellSortMayoraMenor(nuevo);
		nueva= new ListaDoblementeEncadenada<Comparendo>();
		pegar(nuevo, nueva);
		
		return nueva;
	}
	public ListaDoblementeEncadenada<Comparendo> buscarComparendosPorInfraccion(String infraccion)
	{
		ListaDoblementeEncadenada resp = new ListaDoblementeEncadenada();
		ListaDoblementeEncadenada colaAuxiliar= datosCola;
		int length = colaAuxiliar.darLongitud();
		for(int i=0; i< length ;i++)
		{
			Comparendo temp= (Comparendo)colaAuxiliar.eliminarComienzo();
			
			if(infraccion.compareTo(temp.getInfraccion())==0)
			{
				resp.insertarComienzo(temp);;

			}
		}
		Comparable[]  aOrdenar = copiarComparendos(resp,1);
		shellSortMayoraMenor(aOrdenar);
		ListaDoblementeEncadenada retorno = new ListaDoblementeEncadenada();
		pegar(aOrdenar,retorno);
		
		return retorno;
	}
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosInfraccionPorFechas(String fechaI, String fechaF)
	{
		ListaDoblementeEncadenada nuevo1= buscarComparendosPorFecha(fechaI);
		ListaDoblementeEncadenada nuevo2= buscarComparendosPorFecha(fechaF);
		
		ListaDoblementeEncadenada fecha1= new ListaDoblementeEncadenada<>();
		
		int i=0;
		Node puntero=nuevo1.darCabeza2();
		String infraccionactual=null;
		if(puntero!=null)
		{
			infraccionactual=((Comparendo)puntero.darE()).getInfraccion();
		}
		int cont=1;
		while(i<nuevo1.darLongitud() && puntero!=null)
		{
			Comparendo actualsiguiente=null;
			
			String infrasiguiente=null;
			
			if(puntero.darSiguiente()!=null){
			 actualsiguiente=(Comparendo)puntero.darSiguiente().darE();
			 infrasiguiente=actualsiguiente.getInfraccion();
			}
			
			if(infrasiguiente==null)
			{
				fecha1.insertarComienzo( new InfraccionFechas(infraccionactual,cont,0));
			}
			
			else if(infraccionactual.equals(infrasiguiente))
			{
				cont++;
			}
			else if(!infraccionactual.equals(infrasiguiente))
			{
				fecha1.insertarComienzo(new InfraccionFechas(infraccionactual,cont,0));
				cont=1;
				infraccionactual=infrasiguiente;
			}
			
			puntero=puntero.darSiguiente();
			i++;
		}
		
		i=0;
		puntero=nuevo2.darCabeza2();
		infraccionactual=null;
		if(puntero!=null)
		{
			infraccionactual=((Comparendo)puntero.darE()).getInfraccion();
		}
		cont=1;
		
		while(i<nuevo2.darLongitud() && puntero!=null)
		{
			Comparendo actualsiguiente=null;
			
			String infrasiguiente=null;
			
			if(puntero.darSiguiente()!=null){
			 actualsiguiente=(Comparendo)puntero.darSiguiente().darE();
			 infrasiguiente=actualsiguiente.getInfraccion();
			}
			
			if(infrasiguiente==null)
			{
				fecha1.insertarComienzo(new InfraccionFechas(infraccionactual,0,cont));
			}
			
			else if(infraccionactual.equals(infrasiguiente))
			{
				cont++;
			}
			else if(!infraccionactual.equals(infrasiguiente))
			{
				fecha1.insertarComienzo(new InfraccionFechas(infraccionactual,0,cont));
				cont=1;
				infraccionactual=infrasiguiente;
			}
			
			puntero=puntero.darSiguiente();
			i++;
		}
		
		Comparable[] nuevo= copiar(fecha1);
		shellSortMenoraMayor(nuevo);
		ListaDoblementeEncadenada casiretorno=new ListaDoblementeEncadenada<>();
		pegar(nuevo, casiretorno);
		
		ListaDoblementeEncadenada retorno=new ListaDoblementeEncadenada<>();
		i=0;
		puntero=casiretorno.darCabeza2();
		while(i<casiretorno.darLongitud() && puntero!=null)
		{
			infraccionactual=((InfraccionFechas)puntero.darE()).getInfra();
			
			InfraccionFechas actualsiguiente=null;
			
			String infrasiguiente=null;
			
			if(puntero.darSiguiente()!=null){
			 actualsiguiente=(InfraccionFechas)puntero.darSiguiente().darE();
			 infrasiguiente=actualsiguiente.getInfra();
			}
			
			if(infrasiguiente==null)
			{
				InfraccionFechas anterior=null;
				
				if(puntero.darAnterior()!=null)
				{
				anterior=(InfraccionFechas) puntero.darAnterior().darE();
				
				
					if(!infraccionactual.equals(anterior.getInfra()))
					{
						retorno.insertarFinal(puntero.darE());
					}
				}
				else
				{
					retorno.insertarFinal(puntero.darE());
				}
				
			}
			
			else if(infraccionactual.equals(infrasiguiente))
			{
				int nff=((InfraccionFechas)puntero.darE()).getFechafinal()+actualsiguiente.getFechafinal();
				int nfi=((InfraccionFechas)puntero.darE()).getFechainicial()+actualsiguiente.getFechainicial();
				retorno.insertarFinal(new InfraccionFechas(infraccionactual,nfi,nff));
			}
			else if(!infraccionactual.equals(infrasiguiente))
			{
				String anterior=null;
				
				if(puntero.darAnterior()!=null)
				{
					anterior=((InfraccionFechas) puntero.darAnterior().darE()).getInfra();
				
					if(!infraccionactual.equals(anterior))
					{
					retorno.insertarFinal(puntero.darE());
					}
				
				}
				else 
				{
					retorno.insertarFinal(puntero.darE());
				}
			}
			
			puntero=puntero.darSiguiente();
			i++;
		}
		
		
		return retorno;
	}
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosInfraccionPorServicio()
	{
		return null;
	}
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosInfraccionPorLocalidadyFechas(String localidad,String fechaI,String fechaF)
	{
		String[] datosreal=fechaI.split("/");
		
		int ano=Integer.parseInt(datosreal[0]);
		int mes=Integer.parseInt(datosreal[1]);
		int dia= Integer.parseInt(datosreal[2]);
		Fecha fecha1=new Fecha(dia,mes,ano);
		
		datosreal=fechaI.split("/");
		
		ano=Integer.parseInt(datosreal[0]);
		mes=Integer.parseInt(datosreal[1]);
		dia= Integer.parseInt(datosreal[2]);
		Fecha fecha2=new Fecha(dia,mes,ano);
		
		Node puntero= datosCola.darCabeza2();
		int i=0;
		ListaDoblementeEncadenada<Comparendo> comparendosfechas= new ListaDoblementeEncadenada<Comparendo>();
		while(i < datosCola.darLongitud())
		{
			datosreal=((Comparendo)puntero.darE()).getFecha().split("/");
			
			int ano1=Integer.parseInt(datosreal[0]);
			int mes1=Integer.parseInt(datosreal[1]);
			int dia1= Integer.parseInt(datosreal[2]);
			Fecha actual=new Fecha(dia1, mes1, ano1);
			
			String localidadactual=((Comparendo)puntero.darE()).getLocalidad();
			
			//System.out.println(actual.compareTo(fecha1) >= 0 );
			//System.out.println(actual.compareTo(fecha2)<= 0);
			//System.out.println(localidadactual.equalsIgnoreCase(localidad));
			if(actual.compareTo(fecha1) >= 0 && actual.compareTo(fecha2) <= 0 && localidadactual.equalsIgnoreCase(localidad))
			{
				comparendosfechas.insertarFinal(((Comparendo)puntero.darE()));
			}
			i++;
			puntero=puntero.darSiguiente();
		}
		Comparable[] nuevo= copiarComparendos(comparendosfechas,0);
		
		shellSortMenoraMayor(nuevo);
		comparendosfechas= new ListaDoblementeEncadenada<Comparendo>();
		pegar(nuevo, comparendosfechas);
		
		ListaDoblementeEncadenada<String> retorno= new ListaDoblementeEncadenada<>();
		
		i=0;
		puntero=comparendosfechas.darCabeza2();
		String infraccionactual=null;
		if(puntero!=null)
		{
			infraccionactual=((Comparendo)puntero.darE()).getInfraccion();
		}
		int cont=1;
		
		while(i<comparendosfechas.darLongitud() && puntero!=null)
		{
			Comparendo actualsiguiente=null;
			
			String infrasiguiente=null;
			
			if(puntero.darSiguiente()!=null){
			 actualsiguiente=(Comparendo)puntero.darSiguiente().darE();
			 infrasiguiente=actualsiguiente.getInfraccion();
			}
			
			if(infrasiguiente==null)
			{
				retorno.insertarFinal(infraccionactual+"        | "+cont);
			}
			
			else if(infraccionactual.equals(infrasiguiente))
			{
				cont++;
			}
			else if(!infraccionactual.equals(infrasiguiente))
			{
				retorno.insertarFinal(infraccionactual+"        | "+cont);
				cont=1;
				infraccionactual=infrasiguiente;
			}
			
			puntero=puntero.darSiguiente();
			i++;
		}
		
		
		return retorno;
	}
	
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosNInfraccionesPorFechas(String n,String fechaI,String fechaF)
	{
		String[] datosreal=fechaI.split("/");
		
		int ano=Integer.parseInt(datosreal[0]);
		int mes=Integer.parseInt(datosreal[1]);
		int dia= Integer.parseInt(datosreal[2]);
		Fecha fecha1=new Fecha(dia,mes,ano);
		
		datosreal=fechaI.split("/");
		
		ano=Integer.parseInt(datosreal[0]);
		mes=Integer.parseInt(datosreal[1]);
		dia= Integer.parseInt(datosreal[2]);
		Fecha fecha2=new Fecha(dia,mes,ano);
		
		Node puntero= datosCola.darCabeza2();
		int i=0;
		ListaDoblementeEncadenada<Comparendo> comparendosfechas= new ListaDoblementeEncadenada<Comparendo>();
		while(i < datosCola.darLongitud())
		{
			datosreal=((Comparendo)puntero.darE()).getFecha().split("/");
			
			int ano1=Integer.parseInt(datosreal[0]);
			int mes1=Integer.parseInt(datosreal[1]);
			int dia1= Integer.parseInt(datosreal[2]);
			Fecha actual=new Fecha(dia1, mes1, ano1);
			
			String localidadactual=((Comparendo)puntero.darE()).getLocalidad();
			
			if(actual.compareTo(fecha1) >= 0 && actual.compareTo(fecha2) <= 0)
			{
				comparendosfechas.insertarFinal(((Comparendo)puntero.darE()));
			}
			i++;
			puntero=puntero.darSiguiente();
		}
		
		Comparable[] nuevo= copiarComparendos(comparendosfechas,0);
		
		shellSortMenoraMayor(nuevo);
		comparendosfechas= new ListaDoblementeEncadenada<Comparendo>();
		pegar(nuevo, comparendosfechas);
		
		ListaDoblementeEncadenada retorno= new ListaDoblementeEncadenada<>();
		
		i=0;
		puntero=comparendosfechas.darCabeza2();
		String infraccionactual=null;
		if(puntero!=null)
		{
			infraccionactual=((Comparendo)puntero.darE()).getInfraccion();
		}
		int cont=1;
		
		while(i<comparendosfechas.darLongitud() && puntero!=null)
		{
			Comparendo actualsiguiente=null;
			
			String infrasiguiente=null;
			
			if(puntero.darSiguiente()!=null){
			 actualsiguiente=(Comparendo)puntero.darSiguiente().darE();
			 infrasiguiente=actualsiguiente.getInfraccion();
			}
			
			if(infrasiguiente==null)
			{
				retorno.insertarFinal(new InfraccionCantidad(infraccionactual,cont));
			}
			
			else if(infraccionactual.equals(infrasiguiente))
			{
				cont++;
			}
			else if(!infraccionactual.equals(infrasiguiente))
			{
				retorno.insertarFinal(new InfraccionCantidad(infraccionactual,cont));
				cont=1;
				infraccionactual=infrasiguiente;
			}
			
			puntero=puntero.darSiguiente();
			i++;
		}
		
		Comparable[] retorno1= copiar(retorno);
		shellSortMayoraMenor(retorno1);
		ListaDoblementeEncadenada retornofinal= new ListaDoblementeEncadenada();
		
		
		
		int z=0;
		while(z < Integer.parseInt(n) && z <retorno1.length)
		{
			retornofinal.insertarFinal(retorno1[z]);
			z++;
		}
		
		return retornofinal;
	}
	public ListaDoblementeEncadenada<String>  mostrarASCIICantidadComparendosPorLocalidad()
	{
		return null;
	}
	
	
	public void shellSortMenoraMayor(Comparable datos[])
	{
		
		int N=datos.length;
		int h=1;
		while(h<N/3)
			h=3*h+1;
		while(h>=1){
			for(int i=h;i<N;i++)
			{
					for(int j=i;j>=h && less(datos[j], datos[j-h]);j-=h)
					{
						exch(datos,j,j-h);
					}
			}
			h=h/3;
		}
		

		
	}
	
	public void shellSortMayoraMenor(Comparable datos[])
	{
		
		int N=datos.length;
		int h=1;
		while(h<N/3)
			h=3*h+1;
		while(h>=1){
			for(int i=h;i<N;i++)
			{
					for(int j=i;j>=h && high(datos[j], datos[j-h]);j-=h)
					{
						exch(datos,j,j-h);
					}
			}
			h=h/3;
		}
		
	

		
	}
	
	private boolean less(Comparable v,Comparable w)
	{
		return v.compareTo(w) < 0;
	}
	
	private boolean high(Comparable v,Comparable w)
	{
		return v.compareTo(w) > 0;
	}
	
	private void exch(Comparable[] datos,int i, int j)
	{
		Comparable t=datos[i];
		datos[i]=datos[j];
		datos[j]=t;
	}
	
	public Comparable[] copiarComparendos()
	{
		int i=0;
		Node puntero=datosCola.darCabeza2();
		Comparable[] arreglo= new Comparable[datosCola.darLongitud()];
		while(i<datosCola.darLongitud())
		{
			arreglo[i]= puntero.darE();
			puntero=puntero.darSiguiente();
			i++;
		}
		return arreglo;
		
	}
	
	public Comparable[] copiar(ListaDoblementeEncadenada datos)
	{
		int i=0;
		Node puntero=datos.darCabeza2();
		Comparable[] arreglo= new Comparable[datos.darLongitud()];
		while(i<datos.darLongitud())
		{
			arreglo[i]= puntero.darE();
			puntero=puntero.darSiguiente();
			i++;
		}
		return arreglo;
		
	}
	
	public Comparable[] copiarComparendos(ListaDoblementeEncadenada datos,int n)
	{
		int i=0;
		Node puntero=datos.darCabeza2();
		Comparable[] arreglo= new Comparable[datos.darLongitud()];
		while(i<datos.darLongitud())
		{
			arreglo[i]= puntero.darE();
			((Comparendo)puntero.darE()).setConstanteComparaciones(n);
			puntero=puntero.darSiguiente();
			i++;
		}
		return arreglo;
		
	}
	
	public void pegar(Comparable[] copia, ListaDoblementeEncadenada nuevo)
	{
		int i=0;
		while(i<copia.length)
		{
			nuevo.insertarFinal(copia[i]);
			i++;
		}
	}
	
	




}

