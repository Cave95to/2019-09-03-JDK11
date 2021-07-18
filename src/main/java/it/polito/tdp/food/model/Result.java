package it.polito.tdp.food.model;

public class Result {
	
	private String vertice;
	private Integer peso;
	public Result(String vertice, Integer peso) {
		super();
		this.vertice = vertice;
		this.peso = peso;
	}
	public String getVertice() {
		return vertice;
	}
	public void setVertice(String vertice) {
		this.vertice = vertice;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return vertice + ", " + peso + "\n";
	}
	
	

}
