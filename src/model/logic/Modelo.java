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
<<<<<<< HEAD
	private ListaEncadenadaCola<Comparendo> datosCola;
=======
	private ListaDoblementeEncadenada datosCola;
>>>>>>> partePorras

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
		try {
		//Definir mejor la entrada para el lector de json
		long inicio = System.currentTimeMillis();
		long inicio2 = System.nanoTime();
		String dir= "./data/comparendos_dei_2018.geojson.json";
		File archivo= new File(dir);
		JsonReader reader= new JsonReader( new InputStreamReader(new FileInputStream(archivo)));
		JsonObject gsonObj0= JsonParser.parseReader(reader).getAsJsonObject();

		JsonArray comparendos=gsonObj0.get("features").getAsJsonArray();
		
		double menorlatitud=90;
		double mayorlatitud=-90;
		double menorlongitud=180;
		double mayorlongitud=-180;
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
		catch (Exception e){
			e.printStackTrace();
		}


	}

	
	

	/**
<<<<<<< HEAD
	 * Busca el grupo de mayor longitud con infracci�n igual de forma consecutiva.
	 * @return Cola con el grupo con mayor longitud.
=======
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
>>>>>>> partePorras
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
<<<<<<< HEAD
			Comparendo c=(Comparendo) datosCola.eliminarComienzo();

			if(actual.esListaVacia())
=======
			if(((Comparendo)puntero.darE()).getLocalidad().equalsIgnoreCase(localidad))
>>>>>>> partePorras
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
		Node puntero=colaAuxiliar.darCabeza2();
		int i=0;
		while(i<length)
		{
			Comparendo temp= (Comparendo) puntero.darE();
			
			if(infraccion.equalsIgnoreCase(temp.getInfraccion()))
			{
				resp.insertarComienzo(temp);

			}
			i++;
			puntero=puntero.darSiguiente();
		}
		Comparable[]  aOrdenar = copiarComparendos(resp,1);
		shellSortMenoraMayor(aOrdenar);
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
<<<<<<< HEAD

	/**
	 * Busca en una n cantidad de comparendos, la cantidad que contienen la infracci�n pasada por parametro.
	 * @param n. NUmero de comaprendos a revisar.
	 * @param infraccion. C�digo de la infracci�n.
	 * @return Cola con los comparendos que ten�an la infracci�n pasada por parametro.
	 */
	public ListaEncadenadaCola buscarNcomparendosporInfraccion(int n,String infraccion)
=======
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosInfraccionPorServicio()
>>>>>>> partePorras
	{
		
		Node puntero= datosCola.darCabeza2();
		int i=0;
		ListaDoblementeEncadenada<Comparendo> particular= new ListaDoblementeEncadenada<Comparendo>();
		ListaDoblementeEncadenada<Comparendo> publico= new ListaDoblementeEncadenada<Comparendo>();
		while(i<datosCola.darLongitud())
		{
			if(((Comparendo)puntero.darE()).getTiposervi().equalsIgnoreCase("particular"))
			{
				particular.insertarFinal(((Comparendo)puntero.darE()));	
			}
			
			else if(((Comparendo)puntero.darE()).getTiposervi().equalsIgnoreCase("Público"))
			{
				publico.insertarFinal(((Comparendo)puntero.darE()));
			}
			
			
			i++;
			puntero=puntero.darSiguiente();
		}
		
		Comparable[] particular2= copiarComparendos(particular,0);
		Comparable[] publico2=copiarComparendos(publico,0);
		shellSortMenoraMayor(particular2);
		shellSortMenoraMayor(publico2);
		particular= new ListaDoblementeEncadenada<Comparendo>();
		publico= new ListaDoblementeEncadenada<Comparendo>();
		pegar(particular2, particular);
		pegar(publico2, publico );
		
		
		ListaDoblementeEncadenada partiypubli= new ListaDoblementeEncadenada<>();
		
		i=0;
		puntero=particular.darCabeza2();
		String infraccionactual=null;
		
		if(puntero!=null)
		{
			infraccionactual=((Comparendo)puntero.darE()).getInfraccion();
		}
		
		int cont=1;
		while(i<particular.darLongitud() && puntero!=null)
		{
			Comparendo actualsiguiente=null;
			
			String infrasiguiente=null;
			
			if(puntero.darSiguiente()!=null){
			 actualsiguiente=(Comparendo)puntero.darSiguiente().darE();
			 infrasiguiente=actualsiguiente.getInfraccion();
			}
			
			if(infrasiguiente==null)
			{
				partiypubli.insertarComienzo( new InfraccionFechas(infraccionactual,cont,0));
			}
			
			else if(infraccionactual.equals(infrasiguiente))
			{
				cont++;
			}
			else if(!infraccionactual.equals(infrasiguiente))
			{
				partiypubli.insertarComienzo(new InfraccionFechas(infraccionactual,cont,0));
				cont=1;
				infraccionactual=infrasiguiente;
			}
			
			puntero=puntero.darSiguiente();
			i++;
		}
		
		i=0;
		puntero=publico.darCabeza2();
		infraccionactual=null;
		
		if(puntero!=null)
		{
			infraccionactual=((Comparendo)puntero.darE()).getInfraccion();
		}
		
		cont=1;
		while(i<publico.darLongitud() && puntero!=null)
		{
			Comparendo actualsiguiente=null;
			
			String infrasiguiente=null;
			
			if(puntero.darSiguiente()!=null){
			 actualsiguiente=(Comparendo)puntero.darSiguiente().darE();
			 infrasiguiente=actualsiguiente.getInfraccion();
			}
			
			if(infrasiguiente==null)
			{
				partiypubli.insertarComienzo( new InfraccionFechas(infraccionactual,0,cont));
			}
			
			else if(infraccionactual.equals(infrasiguiente))
			{
				cont++;
			}
			else if(!infraccionactual.equals(infrasiguiente))
			{
				partiypubli.insertarComienzo(new InfraccionFechas(infraccionactual,0,cont));
				cont=1;
				infraccionactual=infrasiguiente;
			}
			
			puntero=puntero.darSiguiente();
			i++;
		}
		
		Comparable[] nuevo= copiar(partiypubli);
		shellSortMenoraMayor(nuevo);
		ListaDoblementeEncadenada casiretorno=new ListaDoblementeEncadenada<>();
		pegar(nuevo,casiretorno);
		
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
<<<<<<< HEAD

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
=======
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosInfraccionPorLocalidadyFechas(String localidad,String fechaI,String fechaF)
>>>>>>> partePorras
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
<<<<<<< HEAD
			retorno="No existe informaci�n acerca del comparendo con ID = "+idobject;
=======
			infraccionactual=((Comparendo)puntero.darE()).getInfraccion();
>>>>>>> partePorras
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
	public String mostrarASCIICantidadComparendosPorLocalidad()
	{
		String respuesta= "Aproximaci�n del n�mero de comparendos por localidad. \n";
		int contadorComparendos=0;
		Comparendo[] nuevoArreglo= new Comparendo[datosCola.darLongitud()];
		
		String[] localidades={"USAQUEN","CHAPINERO","SANTA FE","SAN CRISTOBAL","USME","TUNJUELITO","BOSA","KENNEDY","FONTIBON","ENGATIVA","SUBA","BARRIOS UNIDOS","TEUSAQUILLO","LOS MARTIRES","ANTONIO NARIÑO","PUENTE ARANDA","LA CANDELARIA","RAFAEL URIBE URIBE","CIUDAD BOLIVAR","SUMAPAZ","BOGOTA D.C.","Otro"};
		
		Node actual= datosCola.darCabeza2();
		int i =0;
		while(actual!=null)
		{
			nuevoArreglo[i] = (Comparendo)actual.darE();
			((Comparendo)actual.darE()).setConstanteComparaciones(2);
			actual=actual.darSiguiente();
			i++;
		}
		
		
		shellSortMenoraMayor(nuevoArreglo);
		String LocalidadActual = nuevoArreglo[0].getLocalidad();
		for (int j = 0; j < nuevoArreglo.length; j++) {
			
			if(!LocalidadActual.equals(nuevoArreglo[j].getLocalidad()))
			{
				boolean encontrado=false;
				for(int x=0;x<localidades.length && !encontrado;x++)
				{
					if(localidades[x]!=null){
					if(localidades[x].equalsIgnoreCase(LocalidadActual))
					{
						localidades[x]=null;
						encontrado=true;
					}
					}
				}
				
				int cantidadAsteriscos = contadorComparendos/50;
				int diferencia = 16- LocalidadActual.length();
				respuesta += LocalidadActual;
				for (int k = 0; k < diferencia; k++) {
					respuesta+= "-";
				}
				respuesta+= "|";
				for (int k = 0; k < cantidadAsteriscos; k++) {
					respuesta+= "*";
				}
				if(contadorComparendos % 50 !=0)
					respuesta+="*";
				respuesta+="\n";
				
				
				contadorComparendos=0;
				LocalidadActual= nuevoArreglo[j].getLocalidad();
			}
			contadorComparendos++;
		}
		
		for(int x=0;x<localidades.length;x++)
		{
			if(localidades[x]!=null)
			{
				int diferencia = 16- localidades[x].length();
				respuesta += localidades[x];
				for (int k = 0; k < diferencia; k++) {
					respuesta+= "-";
				}
				respuesta+= "|";
				respuesta+="sin comparendos";
				respuesta+="\n";
			}
		}
		
		return respuesta;
	}
	
	
<<<<<<< HEAD
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
=======
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
		
>>>>>>> partePorras

		
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
	
	

	private static void exch(Comparable[] datos,int i, int j)
	{
		Comparable t=datos[i];
		datos[i]=datos[j];
		datos[j]=t;
	}



}

