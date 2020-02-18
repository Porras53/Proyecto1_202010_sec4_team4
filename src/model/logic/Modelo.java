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
		String dir= "./data/comparendos_dei_2018_small.geojson";
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
		return null;
	}
	
	public Comparendo buscarPrimerComparendoInfraccion(String codigoinfraccion)
	{
		return null;
	}
	
	public ListaDoblementeEncadenada<Comparendo> buscarComparendosPorFecha(String fecha)
	{
		return null;
	}
	public ListaDoblementeEncadenada<Comparendo> buscarComparendosPorInfraccion(String infraccion)
	{
		return null;
	}
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosInfraccionPorFechas(String fechaI, String fechaF)
	{
		return null;
	}
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosInfraccionPorServicio()
	{
		return null;
	}
	
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosInfraccionPorLocalidadyFechas(String localidad,String FechaI,String fechaF)
	{
		return null;
	}
	public ListaDoblementeEncadenada<String>  buscarCantidadComparendosNInfraccionesPorFechas(String n,String FechaI,String fechaF)
	{
		return null;
	}
	public ListaDoblementeEncadenada<String>  mostrarASCIICantidadComparendosPorLocalidad()
	{
		return null;
	}
	
	




}

