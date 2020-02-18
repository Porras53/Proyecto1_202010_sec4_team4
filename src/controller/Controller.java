package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;


import model.data_structures.ListaDoblementeEncadenada;
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

			int option = lector.nextInt();
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
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
