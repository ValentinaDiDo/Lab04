package it.polito.tdp.lab04.model;

import java.util.Objects;

public class Iscritti {

	private int matricola;
	private String codins;
	public Iscritti(int matricola, String codins) {
		super();
		this.matricola = matricola;
		this.codins = codins;
	}
	public int getMatricola() {
		return matricola;
	}
	public String getCodins() {
		return codins;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codins, matricola);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Iscritti other = (Iscritti) obj;
		return Objects.equals(codins, other.codins) && matricola == other.matricola;
	}
	@Override
	public String toString() {
		return "Iscritti [matricola=" + matricola + ", codins=" + codins + "]";
	}
	
	
}
