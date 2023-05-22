package it.sincrono.repositories.impl;

public interface SqlStrings {

	//SQL ANAGRAFICA
	public final String SQL_LIST_ANAGRAFICA_FILTER = "SELECT Distinct a.id,a.nome,a.cognome,e.nominativo,a.mail_aziendale,a.cellulare_privato,a.cf,c.data_fine_rapporto,e.data_fine FROM anagrafica a INNER JOIN storico_contratti b ON a.id = b.id_anagrafica INNER JOIN contratto c  ON b.id_contratto = c.id INNER JOIN storico_commesse d  ON a.id = b.id_anagrafica INNER JOIN commessa e ON d.id_commessa = e.id INNER JOIN tipo_livelli_contrattuali f ON f.id=c.id_tipo_livello INNER JOIN tipo_contratto g ON g.id=c.id_tipo_contratto INNER JOIN tipo_contratto_nazionale  i ON i.id=c.id_contratto_nazionale where 1=1 {0}";
	
	//SQL CONTRATTI
	public final String SQL_ORGANICO = "SELECT b.descrizione AS azienda, COUNT(a.id) AS numeroDipendenti, (SELECT COUNT(a1.id_tipo_contratto) FROM contratto a1 WHERE a1.id_tipo_contratto=1 AND a1.id_tipo_azienda = b.id ) AS indeterminati, (SELECT COUNT(a2.id_tipo_contratto) FROM contratto a2 WHERE a2.id_tipo_contratto=2 AND a2.id_tipo_azienda = b.id ) AS determinati, (SELECT COUNT(a3.id_tipo_contratto) FROM contratto a3 WHERE a3.id_tipo_contratto=3 AND a3.id_tipo_azienda = b.id) AS apprendistato, (SELECT count(a4.id_tipo_contratto) FROM contratto a4 WHERE a4.id_tipo_contratto=4 AND a4.id_tipo_azienda = b.id) AS consulenza, (SELECT count(a5.id_tipo_contratto) FROM contratto a5 WHERE a5.id_tipo_contratto=5 AND a5.id_tipo_azienda = b.id) AS stage, (SELECT count(a6.id_tipo_contratto) FROM contratto a6 WHERE a6.id_tipo_contratto=6 AND a6.id_tipo_azienda = b.id) AS partitaiva, (SELECT indeterminati/10 + determinati/10 FROM contratto a6 WHERE  a6.id_tipo_azienda = b.id) AS potenziale_stage, (SELECT potenziale_stage-stage  FROM contratto a6 WHERE  a6.id_tipo_azienda = b.id) AS slot_stage, (SELECT indeterminati*0.66  FROM contratto a6 WHERE  a6.id_tipo_azienda = b.id) AS potenziale_apprendistato, (SELECT potenziale_apprendistato-apprendistato  FROM contratto a6 WHERE  a6.id_tipo_azienda = b.id) AS slot_apprendistato FROM contratto a INNER JOIN tipo_azienda b ON a.id_tipo_azienda = b.id  WHERE a.attivo = 1  GROUP BY a.id_tipo_azienda";	
	//SQL COMMESSE	
	public final String SQL_DASHBOARD = "SELECT a.nominativo as Nominativo, f.descrizione as Tipo_Contratto, g.descrizione as Tipo_Azienda, h.descrizione as CCNL, a.data_inizio as Data_Inizio, a.data_fine as Data_Fine, e.mesi_durata as Mesi FROM commessa a INNER JOIN storico_commesse b ON a.id = b.id_commessa INNER JOIN anagrafica c ON b.id_anagrafica = c.id INNER JOIN storico_contratti d ON c.id = d.id_anagrafica INNER JOIN contratto e ON d.id_contratto = e.id INNER JOIN tipo_contratto f ON e.id_tipo_contratto = f.id INNER JOIN tipo_azienda g ON e.id_tipo_azienda = g.id INNER JOIN tipo_contratto_nazionale h ON e.id_contratto_nazionale = h.id WHERE a.stato = 1";
		
	//SQL RUOLI-UTENTI-PROFILI
	public final String SQL_TREE_RUOLI = "SELECT a FROM Ruolo a WHERE 1 = 1 {0} ORDER BY a.nome";
	public final String SQL_LIST_ANAGRAFICA_PROFILO = "SELECT new Anagrafica(a.id, a.utente, a.nome, a.cognome, a.codiceFiscale, a.partitaIva) FROM Anagrafica a WHERE a.cancellato IS FALSE ORDER BY a.nome, a.cognome";
	public final String SQL_LIST_ANAGRAFICA_PROFILO_FILTER = "SELECT new Anagrafica(a.id, a.utente, a.nome, a.cognome, a.codiceFiscale, a.partitaIva) FROM Anagrafica a WHERE 1 = 1 {0} AND a.cancellato IS FALSE ORDER BY a.nome, a.cognome";
	public final String SQL_LIST_RUOLI_PROFILO = "SELECT a.ruolo.nome FROM Profilo a WHERE a.utente.id = :id ORDER BY a.ruolo.nome";
	public final String SQL_GET_RUOLO_PADRE ="SELECT new Ruolo(r.id, r.nome, r.ruolo) FROM Ruolo r WHERE 1=1 {0}";
	public final String SQL_GET_RUOLO_FIGLIO = "SELECT new Ruolo(r.id,r.nome, r.ruolo, r.ruoli) FROM Ruolo r WHERE 1 = 1 {0}";
	public final String SQL_TREE_FUNZIONI = "SELECT new Funzione(a.id, a.nome) FROM Funzione a WHERE 1 = 1 {0} ORDER BY a.ordinamento";
	public final String SQL_GET_PRIVILEGIO_ESISTENTE = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Privilegio a WHERE a.ruolo.id = :idRuolo AND a.funzione.id = :idFunzione";
	public final String SQL_GET_FUNZIONI_FIGLIE = "SELECT a FROM Funzione a WHERE a.funzione.id = id";
	public final String SQL_GET_RUOLO_UTENTE = "SELECT r.id FROM ruoli r INNER JOIN profili a ON r.id = a.id_ruolo INNER JOIN utenti u ON u.id = a.id_utente WHERE 1=1 AND u.username = '{0}'";


		
	
	
}
