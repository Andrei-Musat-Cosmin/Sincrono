package it.sincrono.repositories.impl;

public interface SqlStrings {

	// SQL ANAGRAFICA
	public final String SQL_LIST_ANAGRAFICA_FILTER = "SELECT DISTINCT (a.id), a.nome, a.cognome, e.nominativo, a.mail_aziendale, a.cellulare_privato, a.cf, c.data_fine_rapporto,e.data_fine FROM anagrafica a INNER JOIN storico_contratti AS b ON a.id = b.id_anagrafica INNER JOIN contratto c ON b.id_contratto = c.id INNER JOIN storico_commesse AS d ON a.id = d.id_anagrafica INNER JOIN commessa e ON d.id_commessa = e.id INNER JOIN tipo_livelli_contrattuali AS f ON f.id=c.id_tipo_livello INNER JOIN tipo_contratto AS g ON g.id=c.id_tipo_contratto INNER JOIN tipo_contratto_nazionale AS i ON i.id=c.id_contratto_nazionale WHERE 1=1 {0}";

	// SQL CONTRATTI
	public final String SQL_ORGANICO = "SELECT b.descrizione AS azienda, COUNT(a.id) AS numeroDipendenti, (SELECT COUNT(a1.id_tipo_contratto) FROM contratto a1 WHERE a1.id_tipo_contratto=1 AND a1.id_tipo_azienda = b.id ) AS indeterminati, (SELECT COUNT(a2.id_tipo_contratto) FROM contratto a2 WHERE a2.id_tipo_contratto=2 AND a2.id_tipo_azienda = b.id ) AS determinati, (SELECT COUNT(a3.id_tipo_contratto) FROM contratto a3 WHERE a3.id_tipo_contratto=3 AND a3.id_tipo_azienda = b.id) AS apprendistato, (SELECT count(a4.id_tipo_contratto) FROM contratto a4 WHERE a4.id_tipo_contratto=4 AND a4.id_tipo_azienda = b.id) AS consulenza, (SELECT count(a5.id_tipo_contratto) FROM contratto a5 WHERE a5.id_tipo_contratto=5 AND a5.id_tipo_azienda = b.id) AS stage, (SELECT count(a6.id_tipo_contratto) FROM contratto a6 WHERE a6.id_tipo_contratto=6 AND a6.id_tipo_azienda = b.id) AS partitaiva, (SELECT (indeterminati + determinati)/10) AS potenziale_stage, (SELECT potenziale_stage - stage) AS slot_stage, (SELECT indeterminati*0.66) AS potenziale_apprendistato, (SELECT potenziale_apprendistato-apprendistato) AS slot_apprendistato FROM contratto a INNER JOIN tipo_azienda b ON a.id_tipo_azienda = b.id WHERE a.attivo = 1 GROUP BY a.id_tipo_azienda";
	
	// SQL COMMESSE
	public final String SQL_DASHBOARD = "SELECT a.nominativo as Nominativo, f.descrizione as Tipo_Contratto, g.descrizione as Tipo_Azienda, h.descrizione as CCNL, a.data_inizio as Data_Inizio, a.data_fine as Data_Fine, e.mesi_durata as Mesi, e.livello_iniziale as LivelloIniziale , e.livello_attuale as LivelloAttuale, e.livello_finale as LivelloFinale FROM commessa a INNER JOIN storico_commesse b ON a.id = b.id_commessa INNER JOIN anagrafica c ON b.id_anagrafica = c.id INNER JOIN storico_contratti d ON c.id = d.id_anagrafica INNER JOIN contratto e ON d.id_contratto = e.id INNER JOIN tipo_contratto f ON e.id_tipo_contratto = f.id INNER JOIN tipo_azienda g ON e.id_tipo_azienda = g.id INNER JOIN tipo_contratto_nazionale h ON e.id_contratto_nazionale = h.id WHERE a.stato = 1";

	// SQL RUOLI-UTENTI-PROFILI
	public final String SQL_TREE_RUOLI = "SELECT a FROM Ruolo a WHERE 1 = 1 {0} ORDER BY a.nome";
	
	
	
	// Possibile aggiunta alle funzioni di anagrafica ( query da modificare)
	//public final String SQL_RUOLO_PROFILO = "SELECT b.nome FROM profili a INNER JOIN ruoli b ON a.id_ruolo = b.id INNER JOIN utenti c ON a.id_utente = c.id WHERE c.id = :id ORDER BY b.nome"; 
	public final String SQL_GET_PRIVILEGIO= "Select p FROM Privilegio p WHERE p.ruolo.id = :id_ruolo AND p.funzione.id = :id_funzione";
	public final String SQL_GET_PRIVILEGIO_ESISTENTE_PADRE = "SELECT 1 FROM Privilegi p WHERE p.funzione.id = :idpadre AND p.ruolo.id = :idruolo";
	public final String SQL_TREE_FUNZIONI = "SELECT a FROM Funzione a WHERE 1 = 1 {0} ORDER BY a.ordinamento";
	public final String SQL_GET_FUNZIONI_FIGLIE = "SELECT a FROM Funzione a WHERE a.funzione.id = id";
	public final String SQL_GET_RUOLO_UTENTE = "SELECT r.id FROM ruoli r INNER JOIN profili a ON r.id = a.id_ruolo INNER JOIN utenti u ON u.id = a.id_utente WHERE 1=1 AND u.username = '{0}'";

}
