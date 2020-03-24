package it.polito.tdp.spellchecker.model;

public class RichWord {
	private String parolaInserita;
	private boolean corretta;
	
	public RichWord(String parolaInserita, boolean corretta) {
		this.parolaInserita = parolaInserita;
		this.corretta = corretta;
	}

	public String getParolaInserita() {
		return parolaInserita;
	}

	public void setParolaInserita(String parolaInserita) {
		this.parolaInserita = parolaInserita;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}
}
