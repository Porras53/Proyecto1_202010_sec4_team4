package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;


import model.data_structures.ListaDoblementeEncadenada;
import model.data_structures.Node;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}



	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String respuesta = "";

		while( !fin ){
			view.printMenu();
try {
			int option = Integer.parseInt(lector.nextLine());
			switch(option){
			case 1:
				modelo = new Modelo();
				view.printMessage("Cargando los comparendos...");
				try {
					modelo.cargar();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
<<<<<<< HEAD
				ListaEncadenadaCola datosCola= (ListaEncadenadaCola) modelo.getDatosCola();
				ListaEncadenadaPila datosPila=(ListaEncadenadaPila) modelo.getDatosPila();

				view.printMessage("Lista de Comparendos cargado");
				view.printMessage("Primer Comparendo = " + datosCola.darCabeza().toString() + "\n---------");
				view.printMessage("Ultimo Comparendo = " + datosPila.darCabeza().toString() + "\n---------");
				view.printMessage("Numero de comparendos = " + datosCola.darLongitud() + "\n---------");
				break;

			case 2:
				view.printMessage("--------- \nProcesando... \n---------");
				ListaEncadenadaCola datosCola3=modelo.buscarMayorCluster();	
				view.printMessage("Numero de comparendos encontrados: "+datosCola3.darLongitud() +" ,con el codigo de infracciï¿½n:"+((Comparendo)datosCola3.darCabeza()).getInfraccion()+"\n---------");
				int i=0;
				while(i<datosCola3.darLongitud())
				{
					Comparendo c=(Comparendo) datosCola3.eliminarComienzo();
					view.printMessage("Comparendo= Codigo Infraccion:"+c.toString2() +"\n---------");
				}

				break;

			case 3:
				view.printMessage("--------- \n Dar la cantidad de comparendos a procesar: \n---------");
				int n= lector.nextInt();
				view.printMessage("--------- \n Dar el codigo de la infraccion a buscar: \n---------");
				String infraccion=lector.next();
				view.printMessage("--------- \n Procesando... \n---------");


				ListaEncadenadaCola datosCola2=modelo.buscarNcomparendosporInfraccion(n, infraccion);
				view.printMessage("Numero de comparendos encontrados: "+datosCola2.darLongitud() +"\n---------");
				int i2=0;
				while(i2<datosCola2.darLongitud())
				{
					Comparendo c= (Comparendo) datosCola2.eliminarComienzo();
					view.printMessage("Comparendo= Codigo Infraccion:"+c.toString2() +"\n---------");
				}

				break;

			case 4: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;

			case 5: 
				System.out.println(" Ingrese la infraccion que desea buscar:");
				String infraccio1B = lector.next();
				Comparendo primero =modelo.method1B(infraccio1B);
				System.out.println("object ID :" + primero.getId());				
				System.out.println("TipoServicio :" + primero.getTiposervi());
				System.out.println("Localidad :" + primero.getLocalidad());
				
				break;	
			case 6: 
				System.out.println(" Ingrese la infraccion que desea buscar:");
				String infraccio2B = lector.next();
				Comparendo[] ArregloComparendo =modelo.method2B(infraccio2B);
				for (int j = 0; j < ArregloComparendo.length; j++) {
					Comparendo actual2B = ArregloComparendo[j];
					System.out.println("object ID :" + actual2B.getId());					
					System.out.println("Fecha_HOra :" + actual2B.getFecha());
					System.out.println("TipoServicio :" + actual2B.getTiposervi());
					System.out.println("Localidad :" + actual2B.getLocalidad());
				}
				
				
				break;	
			case 7:
				System.out.println(modelo.CompararComparendos3B());
				break;
			case 8:
				System.out.println(modelo.generarGrafica3C());
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
=======
				    ListaDoblementeEncadenada datosListaEncadenada= (ListaDoblementeEncadenada) modelo.getDatosListaEncadenada();
				    double[] zonaMinimax=modelo.getZonaminiMax();
				    view.printMessage("Lista de Comparendos cargado");
				    view.printMessage("Primer Comparendo = " + datosListaEncadenada.darCabeza().toString() + "\n---------");
				    view.printMessage("Ultimo Comparendo = " + datosListaEncadenada.darUltimo().toString() + "\n---------");
				    view.printMessage("Numero de comparendos = " + datosListaEncadenada.darLongitud() + "\n---------");
				    view.printMessage(" La zona Minimax: \n---------");
				    view.printMessage(" La menor latitud es: "+zonaMinimax[0]+"\n---------");
				    view.printMessage(" La menor longitud es: "+zonaMinimax[1]+"\n---------");
				    view.printMessage(" La mayor latitud es: "+zonaMinimax[2]+"\n---------");
				    view.printMessage(" La mayor longitud es: "+zonaMinimax[3]+"\n---------");
					break;
					
				case 2:
					view.printMessage("Ingresa la localidad: \n---------"); 
					String localidaddada=lector.next();
					Comparendo c=modelo.buscarPrimerComparendoLocalidad(localidaddada);
					if(c.equals(null))
					{
						 view.printMessage("No se encontró ningún comparendo con la localidad dada. \n---------");
						 view.printMessage("Debería intentar escribir bien la localidad... \n---------");
					}
					else
					{
						 view.printMessage("Primer Comparendo encontrado= " + c.toString() + "\n---------");
					}
					
					break;
					
				case 3: 
					
					view.printMessage("Ingresa el codigo de infraccion: \n---------"); 
					String infracciondada=lector.next();
					Comparendo co=modelo.buscarPrimerComparendoInfraccion(infracciondada);
					if(co.equals(null))
					{
						 view.printMessage("No se encontró ningún comparendo con la localidad dada. \n---------");
						 view.printMessage("Ingrese un código de infracción correcto... \n---------");
					}
					else
					{
						 view.printMessage("Primer Comparendo encontrado= " + co.toString() + "\n---------");
					}
					
					break;
					
				case 4:
					view.printMessage("Ingresa la fecha( Año/Mes/Dia ): \n---------"); 
					String fechadada=lector.next();
					
					try
					{
						String[] modo=fechadada.split("/");
						
						
						int ano= Integer.parseInt(modo[0]);
						int mes1=Integer.parseInt(modo[1]);
						int dia= Integer.parseInt(modo[2]);
						
						if(ano!=2018)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia )20XX/XX/XX");
						}
						else if(mes1<1 && mes1>12)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						else if(dia<1 && dia>31)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
					
					}
					catch(Exception e)
					{
						view.printMessage("Ingresa el correcto formato de fecha  ( Año/Mes/Dia ) 20XX/XX/XX");
					}
					ListaDoblementeEncadenada nueva=modelo.buscarComparendosPorFecha(fechadada);
					
					int i=0;
					Node puntero=nueva.darCabeza2();
					while(i<nueva.darLongitud())
					{
						view.printMessage(puntero.darE().toString() + "\n---------");
						puntero=puntero.darSiguiente();
						i++;
					}
					
					view.printMessage("La cantidad de comparendos la búsqueda fue: "+nueva.darLongitud());
					
					break;
					
				case 5: 
					
					view.printMessage("Ingresa el código de infracción: \n---------"); 
					String codinfra=lector.next();
					
					ListaDoblementeEncadenada nuevaaa=modelo.buscarComparendosPorInfraccion(codinfra);
					
					i=0;
					puntero=nuevaaa.darCabeza2();
					while(i<nuevaaa.darLongitud())
					{
						view.printMessage(puntero.darE().toString() + "\n---------");
						puntero=puntero.darSiguiente();
						i++;
					}
					
					view.printMessage("La cantidad de comparendos la búsqueda fue: "+nuevaaa.darLongitud());
					
					break;
			
				case 6:
					view.printMessage("Ingresa la fecha inicial( Año/Mes/Dia ): \n---------"); 
					String fechadada1=lector.next();
					
					view.printMessage("Ingresa la fecha final( Año/Mes/Dia ): \n---------"); 
					String fechadada2=lector.next();
					
					try
					{
						String[] modo=fechadada1.split("/");
						
						
						int ano= Integer.parseInt(modo[0]);
						int mes1=Integer.parseInt(modo[1]);
						int dia= Integer.parseInt(modo[2]);
						
						if(ano!=2018)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia )20XX/XX/XX");
						}
						else if(mes1<1 && mes1>12)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						else if(dia<1 && dia>31)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						
						modo=fechadada2.split("/");
						
						ano= Integer.parseInt(modo[0]);
						mes1=Integer.parseInt(modo[1]);
						dia= Integer.parseInt(modo[2]);
						
						if(ano!=2018)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia )20XX/XX/XX");
						}
						else if(mes1<1 && mes1>12)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						else if(dia<1 && dia>31)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
					
					}
					catch(Exception e)
					{
						view.printMessage("Ingresa el correcto formato de fecha  ( Año/Mes/Dia ) 20XX/XX/XX");
					}
					
					ListaDoblementeEncadenada nuevo0=modelo.buscarCantidadComparendosInfraccionPorFechas(fechadada1, fechadada2);
					
					view.printMessage("Comparación de comparendos por Infracción en dos fechas:");
					view.printMessage("Infraccion | "+fechadada1+" | "+fechadada2);
					int i2=0;
					Node puntero1=nuevo0.darCabeza2();
					while(i2<nuevo0.darLongitud())
					{
						view.printMessage(puntero1.darE().toString() + "\n---------");
						
						puntero1=puntero1.darSiguiente();
						i2++;
					}
					
					break;
					
				case 7: 
					
					nuevo0=modelo.buscarCantidadComparendosInfraccionPorServicio();
					
					view.printMessage("Comparación de comparendos por servicio particular o público:");
					view.printMessage("Infraccion |  Particular  |  Público ");
					i2=0;
					puntero1=nuevo0.darCabeza2();
					while(i2<nuevo0.darLongitud())
					{
						view.printMessage(puntero1.darE().toString() + "\n---------");
						
						puntero1=puntero1.darSiguiente();
						i2++;
					}
					
					break;
					
				case 8: 
					view.printMessage("Ingrese la localidad: \n---------"); 
					String localidad=lector.next();
					
					view.printMessage("Ingresa la fecha inicial( Año/Mes/Dia ): \n---------"); 
					String fechadada3=lector.next();
					
					view.printMessage("Ingresa la fecha final( Año/Mes/Dia ): \n---------"); 
					String fechadada4=lector.next();
					
					try
					{
						String[] modo=fechadada3.split("/");
						
						
						int ano= Integer.parseInt(modo[0]);
						int mes1=Integer.parseInt(modo[1]);
						int dia= Integer.parseInt(modo[2]);
						
						if(ano!=2018)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia )20XX/XX/XX");
						}
						else if(mes1<1 && mes1>12)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						else if(dia<1 && dia>31)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						
						modo=fechadada4.split("/");
						
						ano= Integer.parseInt(modo[0]);
						mes1=Integer.parseInt(modo[1]);
						dia= Integer.parseInt(modo[2]);
						
						if(ano!=2018)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia )20XX/XX/XX");
						}
						else if(mes1<1 && mes1>12)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						else if(dia<1 && dia>31)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
					
					}
					catch(Exception e)
					{
						view.printMessage("Ingresa el correcto formato de fecha  ( Año/Mes/Dia ) 20XX/XX/XX");
					}
					
					
					ListaDoblementeEncadenada<String> resultado = modelo.buscarCantidadComparendosInfraccionPorLocalidadyFechas(localidad, fechadada3, fechadada4);
					view.printMessage("Comparación de comparendos en "+localidad.toUpperCase() +" del "+fechadada3+" al "+fechadada4);
					view.printMessage("Infraccion | #Comparendos");
					int i3=0;
					
					Node puntero2=resultado.darCabeza2();
					while(i3<resultado.darLongitud())
					{
						view.printMessage(puntero2.darE() + "\n---------");
						
						puntero2=puntero2.darSiguiente();
						i3++;
					}
					
					break;	
					
				case 9: 
					view.printMessage("Ingrese la cantidad de comparendos mayores que quiere ver: \n---------"); 
					String n=lector.next();
					
					view.printMessage("Ingresa la fecha inicial( Año/Mes/Dia ): \n---------"); 
					String fechadada5=lector.next();
					
					view.printMessage("Ingresa la fecha final( Año/Mes/Dia ): \n---------"); 
					String fechadada6=lector.next();
					
					try
					{
						String[] modo=fechadada5.split("/");
						
						
						int ano= Integer.parseInt(modo[0]);
						int mes1=Integer.parseInt(modo[1]);
						int dia= Integer.parseInt(modo[2]);
						
						if(ano!=2018)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia )20XX/XX/XX");
						}
						else if(mes1<1 && mes1>12)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						else if(dia<1 && dia>31)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						
						modo=fechadada6.split("/");
						
						ano= Integer.parseInt(modo[0]);
						mes1=Integer.parseInt(modo[1]);
						dia= Integer.parseInt(modo[2]);
						
						if(ano!=2018)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia )20XX/XX/XX");
						}
						else if(mes1<1 && mes1>12)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
						else if(dia<1 && dia>31)
						{
							view.printMessage("Ingresa el correcto formato de fecha ( Año/Mes/Dia ) 20XX/XX/XX");
						}
					
					}
					catch(Exception e)
					{
						view.printMessage("Ingresa el correcto formato de fecha  ( Año/Mes/Dia ) 20XX/XX/XX");
					}
					
					view.printMessage("Ranking de las "+n+" mayores infracciones del "+fechadada5+" al "+fechadada6);
					view.printMessage("Infraccion | #Comparendos");
					ListaDoblementeEncadenada<String> resultado2 = modelo.buscarCantidadComparendosNInfraccionesPorFechas(n, fechadada5, fechadada6);
					
					
					int i4=0;
					Node puntero3=resultado2.darCabeza2();
					while(i4<resultado2.darLongitud())
					{
						view.printMessage(puntero3.darE().toString() + "\n---------");
						
						puntero3=puntero3.darSiguiente();
						i4++;
					}
					
					break;	
					
				case 10: 
					
					view.printMessage(modelo.mostrarASCIICantidadComparendosPorLocalidad());
					
					break;	
					
				case 11: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
>>>>>>> partePorras
			}
}
catch(Exception e)
{
	e.printStackTrace();
}
		}

	}	
}
