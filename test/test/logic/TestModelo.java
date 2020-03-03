package test.logic;

import static org.junit.Assert.*;

import model.data_structures.ListaDoblementeEncadenada;
import model.logic.Comparendo;
import model.logic.InfraccionFechas;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	private ListaDoblementeEncadenada lista;
	
	@Before
	public void setUp1() {
		modelo=new Modelo();
		lista = modelo.getDatosListaEncadenada();
		lista.insertarFinal(new Comparendo(1,"2018/12/08","Automovil","Público","C02","","fontibon",-74.2,4.02));
		lista.insertarFinal(new Comparendo(2,"2018/11/08","Automovil","particular","B02","","usaquen",-74.2,4.02));
		lista.insertarFinal(new Comparendo(3,"2018/08/08","Automovil","Público","H11","","fontibon",-74.2,4.02));
		lista.insertarFinal(new Comparendo(4,"2018/12/08","Automovil","Público","I07","","san cristobal",-74.2,4.02));
		lista.insertarFinal(new Comparendo(5,"2018/07/08","Automovil","Público","E02","","chapinero",-74.2,4.02));
		lista.insertarFinal(new Comparendo(6,"2018/05/08","Automovil","Público","G06","","fontibon",-74.2,4.02));
		lista.insertarFinal(new Comparendo(7,"2018/05/08","Automovil","Público","A04","","usme",-74.2,4.02));
		lista.insertarFinal(new Comparendo(8,"2018/12/08","Automovil","Público","J02","","bosa",-74.2,4.02));
		lista.insertarFinal(new Comparendo(9,"2018/01/08","Automovil","particular","C02","","fontibon",-74.2,4.02));
		modelo.setDatosCola(lista);
	}


	

	@Test
	public void testBuscarPrimerComparendoLocalidad() {
		// TODO
		setUp1();
		Comparendo c= modelo.buscarPrimerComparendoLocalidad("chapinero");
		assertEquals(5,c.getId());
		
	}
	
	@Test
	public void testbuscarPrimerComparendoInfraccion() {
		// TODO
		setUp1();
		Comparendo c= modelo.buscarPrimerComparendoInfraccion("B02");
		assertEquals(2,c.getId());
	}
	
	@Test
	public void testbuscarComparendosPorFecha() {
		// TODO
		setUp1();
		ListaDoblementeEncadenada c=modelo.buscarComparendosPorFecha("2018/12/08");
		assertEquals(8,((Comparendo)c.darCabeza2().darE()).getId());
		assertEquals("J02",((Comparendo)c.darCabeza2().darE()).getInfraccion());
		assertEquals(4,((Comparendo)c.darCabeza2().darSiguiente().darE()).getId());
		assertEquals("I07",((Comparendo)c.darCabeza2().darSiguiente().darE()).getInfraccion());
	}
	
	@Test
	public void buscarComparendosPorInfraccion() {
		// TODO
		setUp1();
		ListaDoblementeEncadenada c=modelo.buscarComparendosPorInfraccion("C02");
		assertEquals(9,((Comparendo)c.darCabeza2().darE()).getId());
		assertEquals("2018/01/08",((Comparendo)c.darCabeza2().darE()).getFecha());
		assertEquals(1,((Comparendo)c.darCabeza2().darSiguiente().darE()).getId());
		assertEquals("2018/12/08",((Comparendo)c.darCabeza2().darSiguiente().darE()).getFecha());
	}
	
	@Test
	public void testbuscarCantidadComparendosInfraccionPorFechas() {
		// TODO
		setUp1();
		ListaDoblementeEncadenada c=modelo.buscarCantidadComparendosInfraccionPorFechas("2018/12/08", "2018/05/08");
		assertEquals("A04        | 0          | 1",((InfraccionFechas)c.darCabeza2().darE()).toString());
		assertEquals("C02        | 1          | 0",((InfraccionFechas)c.darCabeza2().darSiguiente().darE()).toString());
		
		
	}
	@Test
	public void buscarCantidadComparendosInfraccionPorServicio() {
		// TODO
		setUp1();
		ListaDoblementeEncadenada c=modelo.buscarCantidadComparendosInfraccionPorServicio();
		assertEquals("A04        | 0          | 1",((InfraccionFechas)c.darCabeza2().darE()).toString());
		assertEquals("B02        | 1          | 0",((InfraccionFechas)c.darCabeza2().darSiguiente().darE()).toString());
	}
	@Test
	public void testbuscarCantidadComparendosInfraccionPorLocalidadyFechas() {
		// TODO
		setUp1();
		ListaDoblementeEncadenada c=modelo.buscarCantidadComparendosInfraccionPorLocalidadyFechas("fontibon", "2018/01/08", "2018/12/09");
		assertEquals("C02        | 1",(c.darCabeza2().darE()));
	}
	@Test
	public void buscarCantidadComparendosNInfraccionesPorFechas() {
		// TODO
		setUp1();
		ListaDoblementeEncadenada c=modelo.buscarCantidadComparendosNInfraccionesPorFechas("1", "2018/01/08", "2018/12/08");
		assertEquals("C02        | 1",(c.darCabeza2().darE()).toString());
	}
	@Test
	public void mostrarASCIICantidadComparendosPorLocalidad() {
		// TODO
		setUp1();
		String[] cosas= modelo.mostrarASCIICantidadComparendosPorLocalidad().split("\n");
		assertEquals("bosa------------|*",cosas[1]);
		
		
	}
	

	

}
