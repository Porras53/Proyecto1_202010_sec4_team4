package model.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Random;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import model.data_structures.*;


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
	private ListaEncadenadaCola<Comparendo> datosCola;

	/**
	 * Pila de lista encadenada.
	 */

	private ListaEncadenadaPila datosPila;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datosCola = new ListaEncadenadaCola();
		datosPila = new ListaEncadenadaPila();
	}

	/**
	 * Carga el archivo .JSON en una lista enlazada.
	 * @throws FileNotFoundException. Si no encuentra el archivo.
	 */

	public void cargar() throws FileNotFoundException
	{
		try {
		//Definir mejor la entrada para el lector de json
		long inicio = System.currentTimeMillis();
		long inicio2 = System.nanoTime();
		String dir= "./data/comparendos_dei_2018.geojson.json";
		File archivo= new File(dir);
		JsonReader reader= new JsonReader( new InputStreamReader(new FileInputStream(archivo)));
		JsonObject gsonObj0= JsonParser.parseReader(reader).getAsJsonObject();

		JsonArray comparendos=gsonObj0.get("features").getAsJsonArray();
		int i=0;
		//String clasevehiculo= "";
//		if(gsonObjpropiedades.get("CLASE_VEHI")!=null)
//			 clasevehiculo=gsonObjpropiedades.get("CLASE_VEHI").getAsString();
//		String tiposervi= "";
//		if(gsonObjpropiedades.get("TIPO_SERVI")!=null)
//		 tiposervi=gsonObjpropiedades.get("TIPO_SERVI").getAsString();
//		String infraccion= "";
//		if(gsonObjpropiedades.get("INFRACCION")!=null)
//		 infraccion=gsonObjpropiedades.get("INFRACCION").getAsString();
//		String desinfraccion= "";
//		if(gsonObjpropiedades.get("DES_INFRAC")!=null)
//		 desinfraccion=gsonObjpropiedades.get("DES_INFRAC").getAsString();
//		String localidad	= "";
//		if(gsonObjpropiedades.get("LOCALIDAD")!=null)
//		 localidad=gsonObjpropiedades.get("LOCALIDAD").getAsString();
		while(i<comparendos.size()-1)
		{
			
			JsonElement obj= comparendos.get(i);
			JsonObject gsonObj= obj.getAsJsonObject();

			JsonObject gsonObjpropiedades=gsonObj.get("properties").getAsJsonObject();
			int objid= gsonObjpropiedades.get("OBJECTID").getAsInt();
			String fecha= gsonObjpropiedades.get("FECHA_HORA").getAsString();
			String clasevehiculo=gsonObjpropiedades.get("CLASE_VEHICULO").getAsString();
			String tiposervi=gsonObjpropiedades.get("TIPO_SERVICIO").getAsString();
			String infraccion=gsonObjpropiedades.get("INFRACCION").getAsString();
			String desinfraccion=gsonObjpropiedades.get("DES_INFRACCION").getAsString();
			String localidad=gsonObjpropiedades.get("LOCALIDAD").getAsString();

			JsonObject gsonObjgeometria=gsonObj.get("geometry").getAsJsonObject();

			JsonArray gsonArrcoordenadas= gsonObjgeometria.get("coordinates").getAsJsonArray();
			double longitud= gsonArrcoordenadas.get(0).getAsDouble();
			double latitud= gsonArrcoordenadas.get(1).getAsDouble();

			Comparendo agregar=new Comparendo(objid, fecha, clasevehiculo, tiposervi, infraccion, desinfraccion, localidad, longitud,latitud);
			datosCola.insertarFinal(agregar);
			datosPila.insertarComienzo(agregar);
			i++;
		}
		long fin2 = System.nanoTime();
		long fin = System.currentTimeMillis();

		double tiempo = (double) ((fin - inicio)/1000);
		System.out.println((fin2-inicio2)/1.0e9 +" segundos");
		System.out.println(tiempo +" segundos");
		}
		catch (Exception e){
			e.printStackTrace();
		}


	}


	/**
	 * Busca el grupo de mayor longitud con infracci�n igual de forma consecutiva.
	 * @return Cola con el grupo con mayor longitud.
	 */
	public ListaEncadenadaCola buscarMayorCluster()
	{
		ListaEncadenadaCola mayor= new ListaEncadenadaCola();
		ListaEncadenadaCola actual= new ListaEncadenadaCola();
		int i=0;
		int a=datosCola.darLongitud();
		while(i<a)
		{
			Comparendo c=(Comparendo) datosCola.eliminarComienzo();

			if(actual.esListaVacia())
			{
				actual.insertarFinal(c);
			}
			else{


				if(((Comparendo)actual.darUltimo()).getInfraccion().equals(c.getInfraccion()))
				{
					actual.insertarFinal(c);
				}
				else if(!(((Comparendo)actual.darUltimo()).getInfraccion().equals(c.getInfraccion())))
				{
					if(actual.darLongitud()>mayor.darLongitud())
					{
						mayor= actual;
					}
					actual= new ListaEncadenadaCola();
				}
			}
			i++;
		}
		return mayor;

	}

	/**
	 * Busca en una n cantidad de comparendos, la cantidad que contienen la infracci�n pasada por parametro.
	 * @param n. NUmero de comaprendos a revisar.
	 * @param infraccion. C�digo de la infracci�n.
	 * @return Cola con los comparendos que ten�an la infracci�n pasada por parametro.
	 */
	public ListaEncadenadaCola buscarNcomparendosporInfraccion(int n,String infraccion)
	{
		ListaEncadenadaCola colanueva= new ListaEncadenadaCola();
		int i=0;
		if(datosPila.darLongitud()<n)
		{
			n=datosPila.darLongitud();
		}
		while(i<n)
		{
			Comparendo actual=(Comparendo) datosPila.eliminarComienzo();
			if(actual.getInfraccion().equalsIgnoreCase(infraccion)){
				colanueva.insertarFinal(actual);
			}
			i++;
		}

		return colanueva;
	}

	public Comparendo method1B (String infraccion)
	{
		Comparendo resp =null;
		ListaEncadenadaCola<Comparendo> colaAuxiliar= datosCola;
		int length = colaAuxiliar.darLongitud();
		for(int i=0; i< length && resp==null; i++)
		{
			Comparendo temp=  colaAuxiliar.eliminarComienzo();
			
			if(infraccion.compareTo(temp.getInfraccion())==0)
			{
				resp=temp;

			}
		}
		return resp;
    }
	
	
	
	
	
	
	
	public Comparendo[] method2B (String infraccion)
	{
		ListaEncadenadaPila<Comparendo> resp = new ListaEncadenadaPila<Comparendo>();
		ListaEncadenadaCola<Comparendo> colaAuxiliar= datosCola;
		int length = colaAuxiliar.darLongitud();
		for(int i=0; i< length ;i++)
		{
			Comparendo temp=  colaAuxiliar.eliminarComienzo();
			
			if(infraccion.compareTo(temp.getInfraccion())==0)
			{
				resp.insertarComienzo(temp);

			}
		}
		Comparendo[]  aOrdenar = new Comparendo[resp.darLongitud()];
		int i= 0;
		while(!resp.esListaVacia())
		{
			aOrdenar[i]= resp.eliminarComienzo();
			aOrdenar[i].setConstanteComparaciones(1);
			i++;
		}
		quickSort(aOrdenar);
		
		return aOrdenar;
    }
	
	
	
	public String CompararComparendos3B()
	{
		System.out.println("prueba");
		String respuesta = "Infraccion  |particular  |publico \n";
		int contadorPublico = 0;
		int contadorPrivado =0;

		Comparendo[] nuevoArreglo= new Comparendo[datosCola.darLongitud()];
		ListaEncadenadaCola<Comparendo> lista = datosCola;
				
		int i =0;
		while(i<datosCola.darLongitud())
		{
			
			nuevoArreglo[i] = lista.darCabeza();
			lista.eliminarComienzo();
			
			i++;
			
		}
		

		
			shellSortMenoraMayor(nuevoArreglo); 
			System.out.println("prueba");
		String infraccionActual = nuevoArreglo[0].getInfraccion();
		for (int j = 0; j < nuevoArreglo.length; j++) {

			if(!infraccionActual.equals(nuevoArreglo[j].getInfraccion()))
			{
				respuesta += infraccionActual + "   | "+contadorPrivado+"     |"+contadorPublico+"\n";
				contadorPrivado =0;
				contadorPublico =0;
				infraccionActual = nuevoArreglo[j].getInfraccion();

			}

			if(nuevoArreglo[j].getTiposervi().equals("Particular"))
			{
				contadorPrivado++;
			}
			else
			{
				contadorPublico++;
			}

		}
		return respuesta;
	}
	public void shellSortMenoraMayor(Comparendo datos[])
	{
		
	
		int j;
	    for( int gap = datos.length / 2; gap > 0; gap /= 2 )
	    {
	      for( int i = gap; i < datos.length; i++ )
	      {
	         Comparendo tmp = datos[ i ];
	         for( j = i; j >= gap && tmp.compareTo( datos[ j - gap ] ) < 0; j -= gap )
	         {
	           datos[ j ] = datos[ j - gap ];
	         }
	         datos[ j ] = tmp;
	      }
	    }
	}
	
	
	public String generarGrafica3C()
	{
		String Respuesta= "Aproximación del número de comparendos por localidad. \n";
		int contadorComparendos=0;
		Comparendo[] nuevoArreglo= new Comparendo[datosCola.darLongitud()];
		
		Node<Comparendo> actual= new Node<Comparendo>(datosCola.darCabeza());
		int i =0;
		while(actual!=null)
		{
			nuevoArreglo[i] = actual.darE();
			actual.darE().setConstanteComparaciones(2);
			actual=actual.darSiguiente();
			i++;
		}
		shellSortMenoraMayor(nuevoArreglo);
		String LocalidadActual = nuevoArreglo[0].getLocalidad();
		for (int j = 0; j < nuevoArreglo.length; j++) {
			
			if(!LocalidadActual.equals(nuevoArreglo[j].getLocalidad()))
			{
				int cantidadAsteriscos = contadorComparendos/50;
				int diferencia = 16- LocalidadActual.length();
				Respuesta+= LocalidadActual;
				for (int k = 0; k < diferencia; k++) {
					Respuesta+= "-";
				}
				Respuesta+= "|";
				for (int k = 0; k < cantidadAsteriscos; k++) {
					Respuesta+= "*";
				}
				if(contadorComparendos % 50 !=0)
					Respuesta+="*";
				Respuesta+="\n";
				
				
				contadorComparendos=0;
				LocalidadActual= nuevoArreglo[j].getLocalidad();
			}
			contadorComparendos++;
		}
		return Respuesta;


	}
	/**
	 * Busca y retorna un comparendo en la lista con el ID dado.
	 * @param idobject. ID del comparendo.
	 * @return Informaci�n b�sica del comparendo.
	 */

	public String darInfoPorID(int idobject)
	{
		String retorno=null;
		int cont=0;
		boolean encontrado= false;
		while(cont < datosCola.darLongitud() && !encontrado)
		{
			Comparendo c= (Comparendo) datosCola.darObjeto(cont);
			if(c.getId()==idobject)
			{
				retorno="ID ="+c.getId()+" ,Fecha = "+c.getFecha()+" ,Infraccion ="+c.getClasevehi()+" ,Tipo de servicio="+c.getTiposervi() +" ,Localidad="+c.getLocalidad();
				encontrado= true;
			}
			cont++;
		}

		if(retorno==null)
		{
			retorno="No existe informaci�n acerca del comparendo con ID = "+idobject;
		}

		return retorno;

	}


	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */

	public ListaEncadenadaCola getDatosCola() {
		return datosCola;
	}


	public ListaEncadenadaPila getDatosPila() {
		return datosPila;
	}
	
	
	private static int partition(Comparable[] a,int lo, int hi)
	{
		int i=lo, j=hi+1;
		Comparable v= a[lo];
		while(true)
		{
			while(less(a[++i],v)) if (i==hi) break;
			while (less (v, a[--j])) if(j== lo) break;
			if(i >= j) break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}
	public static void quickSort(Comparable[] a)
	{
		shuffle(a);
		quickSort(a,0,a.length-1);
	}
	
	private static void shuffle(Comparable[] a)
	{
		Random r= new Random();
		for(int i= a.length-1;i>0;i--)
		{
			int index= r.nextInt(i+1);
			Comparable a2= a[index];
			a[index]=a[i];
			a[i]=a2;
		}
	}
	
	public static void quickSort(Comparable[] a,int lo,int hi)
	{
		if(hi<=lo) return;
		int j= partition(a,lo,hi);
		quickSort(a,lo,j-1);
		quickSort(a,j+1,hi);
	}
	
	private static boolean less(Comparable v,Comparable w)
	{
		return v.compareTo(w) < 0;
	}



	private static void exch(Comparable[] datos,int i, int j)
	{
		Comparable t=datos[i];
		datos[i]=datos[j];
		datos[j]=t;
	}



}

