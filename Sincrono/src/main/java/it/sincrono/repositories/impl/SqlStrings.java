package it.sincrono.repositories.impl;

public interface SqlStrings {
	
	public final String SQL_LIST_ANAGRAFICA_FILTER = "SELECT new Anagrafica(a.id ,a.utente , a.nome , a.cognome ,a.codiceFiscale) FROM Anagrafica a WHERE 1=1 {0} ORDER BY a.nome ,a.cognome";

}
