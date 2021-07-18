package it.polito.tdp.food.model;

public class Adiacenza {
	
	String v1;
	String v2;
	Integer peso;
	
	public String getV1() {
		return v1;
	}
	public void setV1(String v1) {
		this.v1 = v1;
	}
	public String getV2() {
		return v2;
	}
	public void setV2(String v2) {
		this.v2 = v2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	public Adiacenza(String v1, String v2, Integer peso) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.peso = peso;
	}
	@Override
	public String toString() {
		return v1 + ", " + v2 + "\n";
	}
	
	

}
