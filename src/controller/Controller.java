package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;


import model.data_structures.ListaEncadenadaCola;
import model.data_structures.ListaEncadenadaPila;
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
				view.printMessage("Numero de comparendos encontrados: "+datosCola3.darLongitud() +" ,con el codigo de infracci�n:"+((Comparendo)datosCola3.darCabeza()).getInfraccion()+"\n---------");
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
			}
}
catch(Exception e)
{
	e.printStackTrace();
}
		}

	}	
}
