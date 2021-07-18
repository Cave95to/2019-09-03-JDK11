package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private FoodDao dao;
	private List<String> vertici;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<Adiacenza> adiacenze;
	private List<String> camminoBest;
	private int pesoBest;
	
	public Model() {
		this.dao = new FoodDao();
	}
	

	public void creaGrafo(Integer calorie) {
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.vertici = new ArrayList<>();
		
		this.vertici = this.dao.getVertici(calorie);
		Graphs.addAllVertices(this.grafo, vertici);
		
		adiacenze = new ArrayList<>();
		adiacenze = this.dao.getAdiacenze();
		
		for(Adiacenza a : adiacenze) {
			String s1 = a.getV1();
			String s2 = a.getV2();
			if(this.grafo.vertexSet().contains(s2) && this.grafo.vertexSet().contains(s1))
				Graphs.addEdge(this.grafo, s1, s2, a.getPeso());
		}
		
	}
	
	public int getNVertici() {
		return this.vertici.size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Set<DefaultWeightedEdge> getArchi() {
		return this.grafo.edgeSet();
	}


	public List<String> getVertici() {
		// TODO Auto-generated method stub
		return this.vertici;
	}
	
	public List<Adiacenza> getAdiacenze() {
		// TODO Auto-generated method stub
		return this.adiacenze;
	}


	public List<Result> getAdiacenti(String porzione) {
		List<Result> result = new ArrayList<>();
		for(String s : Graphs.neighborListOf(this.grafo, porzione)) {
			result.add(new Result(s,(int) this.grafo.getEdgeWeight(this.grafo.getEdge(s, porzione))));
		}
		
		return result;
	}


	public List<String> cercaCammino(String partenza, Integer n) {
		this.camminoBest = new ArrayList<>();
		this.pesoBest = 0;
		
		if(Graphs.neighborListOf(this.grafo, partenza).size()==0)
			return this.camminoBest;
		
		List<String> parziale = new ArrayList<>();
		parziale.add(partenza);
		
		this.calcola(parziale, n);
		
		return this.camminoBest;
	}
	
	private void calcola(List<String> parziale, int n) {
		
		if(parziale.size()==n+1) {
			
			int peso = this.calcolaPeso(parziale);
			if(peso>this.pesoBest) {
				this.pesoBest = peso;
				this.camminoBest = new ArrayList<>(parziale);
			}
			
			return;
		}
		
		// scorro i vicini dell'ultimo inserito e provo 1 a 1 ad aggiungere
		
		for (String vicino : Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1))) {
			
						
			if(!parziale.contains(vicino) ) {
				parziale.add(vicino);
				this.calcola(parziale, n);
				parziale.remove(parziale.size()-1);
			}
		
		}
		

	}


	private int calcolaPeso(List<String> parziale) {
		int peso = 0;
		for(int i= 1; i<parziale.size(); i++) {
			peso = peso + (int)this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(i-1), parziale.get(i)));
		}
		return peso;
	}


	public int getPesoBest() {
		return pesoBest;
	}


	

}
