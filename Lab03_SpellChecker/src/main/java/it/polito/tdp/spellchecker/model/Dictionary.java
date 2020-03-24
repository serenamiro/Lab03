package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	private List<String> elencoParole;

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
	public List<RichWord> spellCheckTest(List<String> inputTextList){
		List<RichWord> daRitornare = new LinkedList<RichWord>();
		for(String s : inputTextList) {
			if(!elencoParole.contains(s.toLowerCase())){
				daRitornare.add(new RichWord(s,false));
			} 
		}
		return daRitornare;
	}
}
