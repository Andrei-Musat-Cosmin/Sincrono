package it.sincrono.repositories.impl;

public interface SqlStrings {

	//SQL ANAGRAFICA
	public final String SQL_LIST_ANAGRAFICA_FILTER = "SELECT new Anagrafica(a.id ,a.utente , a.nome , a.cognome ,a.codiceFiscale) FROM Anagrafica a WHERE 1=1 {0} ORDER BY a.nome ,a.cognome";

	
	//SQL CONTRATTI
	public final String SQL_ORGANICO = "SELECT b.descrizione, COUNT(a.id), (select COUNT(a1.id_tipo_contratto) FROM contratto a1 where a1.id_tipo_contratto=1 and a1.id_tipo_societa = b.id ), (select COUNT(a2.id_tipo_contratto) FROM contratto a2 where a2.id_tipo_contratto=2 and a2.id_tipo_societa = b.id ), (select COUNT(a3.id_tipo_contratto) FROM contratto a3 where a3.id_tipo_contratto=3 and a3.id_tipo_societa = b.id), (select COUNT(a4.id_tipo_contratto) FROM contratto a4 where a4.id_tipo_contratto=4 and a4.id_tipo_societa = b.id), (select COUNT(a5.id_tipo_contratto) FROM contratto a5 where a5.id_tipo_contratto=5 and a5.id_tipo_societa = b.id), (select COUNT(a6.id_tipo_contratto) FROM contratto a6 where a6.id_tipo_contratto=6 and a6.id_tipo_societa = b.id) FROM contratto a INNER JOIN tipo_societa b ON a.id_tipo_societa = b.id WHERE a.attivo = 1 group by a.id_tipo_societa";
	
	//SQL COMMESSE
	
	
	//SQL RUOLI-UTENTI-PROFILI
}
