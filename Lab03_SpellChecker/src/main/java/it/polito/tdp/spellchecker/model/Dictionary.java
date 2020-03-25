package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private List<String> elencoParole;
	String tempo = "";

	public Dictionary() {
		this.elencoParole = new LinkedList<String>();
	}
	
	/**
	 * Metodo che permette di caricare in memoria il dizionario della lingua desiderata.
	 * @param language
	 */
	public void loadDictionary(String language) {
		try {
			FileReader fr = new FileReader("src/main/resources/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				elencoParole.add(word);
			}
			br.close();
		} catch (IOException e){
			System.out.println("Errore nella lettura del file");
		}
	}
	
	public List<String> ordineInput(String input){
		List<String> daRitornare = new LinkedList<String>();
		String temp = new String();
		temp = input.replaceAll("[^a-zA-Z\\s]", "");
		String[] parts = temp.split(" ");
		for(int i=0; i<parts.length; i++) {
			daRitornare.add(parts[i]);
		}
		return daRitornare;
	}
	
	/**
	 * Metodo che esegue il controllo ortografico: se le parole nella Lista in input esistono nel dizionario,
	 * allora sono corrette.
	 * @param inputTextList
	 * @return
	 */
	public List<RichWord> spellCheckTestLinear(List<String> inputTextList){
		long startTime = System.nanoTime();
		List<RichWord> daRitornare = new LinkedList<RichWord>();
		for(String s : inputTextList) {
			if(!elencoParole.contains(s.toLowerCase())){
				daRitornare.add(new RichWord(s,false));
			} 
		}
		long elapsedNanos = System.nanoTime() - startTime;
		tempo = ""+elapsedNanos;
		return daRitornare;
	}
	
	public String tempo() {
		return tempo;
	}
	
	public List<RichWord> spellCheckTestDichotomic (List<String> inputTextList){
		long startTime = System.nanoTime();
		List<RichWord> daRitornare = new LinkedList<RichWord>();
		for(String s : inputTextList) {
			if (ricercaBinaria((String[]) this.elencoParole.toArray(), s) == -1) {
				// vuol dire che la parola non è stata trovata nel dizionario 
				daRitornare.add(new RichWord(s, false));
			}
		}
		long elapsedNanos = System.nanoTime() - startTime;
		tempo = ""+elapsedNanos;
		return daRitornare;
	}
	
	public int ricercaBinaria(String array[], String daCercare) {
		int start = 0;
		int end = array.length-1;
		int centro = 0;
		while (start <= end) {
			centro = (start + end)/2;
			if(daCercare.compareTo(array[centro]) < 0) {
				// confronto le parole per capire in che zona effettuare la ricerca
				end = centro - 1;
			} else {
				if(daCercare.compareTo(array[centro]) > 0) {
					start = centro + 1;
				} else {
					return centro; 
					// Caso: daCercare.equals(array[centro])
				}
			}
		}
		// se arrivo qui, vuol dire che daCercare non è presente in array[]
		return -1;
	}
}
