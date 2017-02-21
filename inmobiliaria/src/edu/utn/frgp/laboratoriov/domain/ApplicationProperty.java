package edu.utn.frgp.laboratoriov.domain;

public class ApplicationProperty {
	
	private String key;
	private String value;
	
	public ApplicationProperty(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public ApplicationProperty() {
		// TODO Auto-generated constructor stub
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
