package it.polito.tdp.borders.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		System.out.println("TestModel -- TODO");
		
		System.out.println("Creo il grafo relativo al 2000");
		model.creaGrafo(1830);
		
		List<String> gradi = model.stampaGradoStati();
		for(String s : gradi) {
			System.out.println(s);
		}
		
		System.out.format("Numero componenti connesse: %d\n", model.calcolaConnesse());
		
		
	}

}
