package edu.utn.seminario.motosnorte.domain;

import java.util.ArrayList;
import java.util.List;

import edu.utn.seminario.motosnorte.helper.Constants;

public class Color {
	private Integer id;
	private String color;
	
	public Color(Integer id, String color) {
		super();
		this.id = id;
		this.color = color;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public static List<Color> getColores(){
		
		List<Color> colores = new ArrayList<>();
		colores.add(new Color(Constants.COLOR_AMARILLO,"Amarillo"));
		colores.add(new Color(Constants.COLOR_AZUL,"Azul"));
		colores.add(new Color(Constants.COLOR_BLANCO,"Blanco"));
		colores.add(new Color(Constants.COLOR_NEGRO,"Negro"));
		colores.add(new Color(Constants.COLOR_ROJO,"Rojo"));
		colores.add(new Color(Constants.COLOR_VERDE,"Verde"));
		return colores;
	}
}
