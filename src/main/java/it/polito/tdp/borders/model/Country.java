package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country implements Comparable<Country>{

	private int CCode;
	private String StateAbb;
	private String StateNme;
	
	public Country(int cCode, String stateAbb, String stateNme) {
		CCode = cCode;
		StateAbb = stateAbb;
		StateNme = stateNme;
	}

	public int getCCode() {
		return CCode;
	}

	public String getStateAbb() {
		return StateAbb;
	}

	public String getStateNme() {
		return StateNme;
	}

	@Override
	public String toString() {
		return StateNme;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CCode, StateAbb, StateNme);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return CCode == other.CCode && Objects.equals(StateAbb, other.StateAbb)
				&& Objects.equals(StateNme, other.StateNme);
	}

	@Override
	public int compareTo(Country o) {
		return this.StateNme.compareTo(o.StateNme);
	}
	
}
