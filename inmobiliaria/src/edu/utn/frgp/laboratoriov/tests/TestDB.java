package edu.utn.frgp.laboratoriov.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.utn.frgp.laboratoriov.dao.CiudadDao;
import edu.utn.frgp.laboratoriov.domain.Ciudad;


public class TestDB {
	
	@Test
	public void testGetCiudades() {
		System.out.println("Probar conexión con base de datos...");
		List<Ciudad> ciudades = new CiudadDao().getCiudades();
		
		Assert.assertFalse(ciudades.isEmpty());
		Assert.assertTrue(ciudades.size() == 4);
		System.out.println("Ciudades:");
		ciudades.iterator().forEachRemaining(c -> System.out.println(c.getCiudad()));
	}
}
