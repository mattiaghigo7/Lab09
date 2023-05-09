package it.polito.tdp.borders.model;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private Graph<Country,DefaultEdge> grafo;
	private List<Country> vertici;
	private List<Country> allVertici;
	private Map<Integer,Country> idMap;
	private BordersDAO dao;

	public Model() {
		//inizializza
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		this.vertici = new ArrayList<>();
		this.allVertici = new ArrayList<>();
		this.idMap = new HashMap<>();
		this.dao = new BordersDAO();
	}
	
	public void creaGrafo(int anno) {
		//aggiungi i vertici
		allVertici = dao.loadAllCountries();
		for(Country c : allVertici) {
			idMap.put(c.getCCode(), c);
		}
		
		List<Border> borders = dao.getCountryPairs(anno, this.idMap);
		List<Country> list = new ArrayList<>();
		for(Border b : borders) {
			list.add(b.getState1());
			list.add(b.getState2());
		}
		list.sort(null);
		for(Country c : list) {
			this.vertici.add(c);
		}
		
		Graphs.addAllVertices(this.grafo, this.vertici);
		
		//aggiungi gli archi
		for(Border b : borders) {
			grafo.addEdge(b.getState1(), b.getState2());
		}
	}
	
	public List<String> stampaGradoStati(){
		List<String> stampa = new ArrayList<>();
		for(Country v : grafo.vertexSet()) {
			String s = "Lo Stato "+v+" ha grado "+grafo.degreeOf(v);
			stampa.add(s);
		}
		return stampa;
	}

	public Integer calcolaConnesse() {
		ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<>(this.grafo);
		Integer num = 0;
		num = inspector.connectedSets().size();
		return num;
	}
	
	public List<Country> raggiungibili(Country partenza){
		DepthFirstIterator<Country,DefaultEdge> visita = new DepthFirstIterator<>(grafo,partenza);
		List<Country> raggiungibili = new ArrayList<>();
		while(visita.hasNext()) {
			Country c = visita.next();
			raggiungibili.add(c);
		}
		return raggiungibili;
	}
	
	public Set<Country> getVertici() {
		return grafo.vertexSet();
	}
}
