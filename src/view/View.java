package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar comparendos.");
			System.out.println("2. Buscar primero por Localidad dada.");
			System.out.println("3. Buscar comparendos por fecha.");
			System.out.println("4. Buscar comparendos por dos fechas.");
			System.out.println("5. Buscar comparendos entre dos fechas y una localidad.");
			System.out.println("6. Buscar los n comparendos con mayor incidencia entre dos fechas.");
			System.out.println("7. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
