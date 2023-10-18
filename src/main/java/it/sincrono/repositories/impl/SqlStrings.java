package it.sincrono.repositories.impl;

public interface SqlStrings {

	// SQL ANAGRAFICA

	public final String SQL_LIST_ANAGRAFICA = "SELECT a.*, c.*, d.*, e.*, f.*, g.*, h.*, i.* FROM anagrafica a INNER JOIN storico_contratti b ON a.id = b.id_anagrafica INNER JOIN contratto c ON b.id_contratto = c.id INNER JOIN tipo_livelli_contrattuali d ON d.id=c.id_tipo_livello INNER JOIN tipo_contratto e ON e.id=c.id_tipo_contratto INNER JOIN tipo_ccnl f ON f.id=c.id_tipo_ccnl INNER JOIN tipo_azienda g ON g.id=a.id_tipo_azienda INNER JOIN tipo_canale_reclutamento h ON h.id=c.id_tipo_canale_reclutamento LEFT OUTER JOIN tipo_causa_fine_rapporto i ON i.id=c.id_tipo_causa_fine_rapporto WHERE c.id=(SELECT max(c1.id) FROM contratto c1 INNER JOIN storico_contratti s ON s.id_contratto=c1.id WHERE s.id_anagrafica=a.id)";

	public final String SQL_ID_ANAGRAFICA = "SELECT a.id,a.nome,a.cognome,a.codice_fiscale FROM anagrafica a";

	// SQL CONTRATTI
	public final String SQL_ORGANICO = "SELECT b.descrizione AS azienda, COUNT(a.id) AS numeroDipendenti, (SELECT COUNT(a1.id_tipo_contratto) FROM contratto a1 INNER JOIN tipo_contratto b1 ON a1.id_tipo_contratto = b1.id WHERE b1.descrizione LIKE \"Indeterminato\" AND a1.id_tipo_azienda = b.id AND a1.attivo = 1) AS indeterminati, (SELECT COUNT(a2.id_tipo_contratto) FROM contratto a2 INNER JOIN tipo_contratto b2 ON a2.id_tipo_contratto = b2.id WHERE b2.descrizione LIKE \"Determinato\" AND a2.id_tipo_azienda = b.id AND a2.attivo = 1) AS determinati, (SELECT COUNT(a3.id_tipo_contratto) FROM contratto a3 INNER JOIN tipo_contratto b3 ON a3.id_tipo_contratto = b3.id WHERE b3.descrizione LIKE \"Apprendistato\" AND a3.id_tipo_azienda = b.id AND a3.attivo = 1) AS apprendistato, (SELECT count(a4.id_tipo_contratto) FROM contratto a4 INNER JOIN tipo_contratto b4 ON a4.id_tipo_contratto = b4.id WHERE b4.descrizione LIKE \"Consulenza\" AND a4.id_tipo_azienda = b.id AND a4.attivo = 1) AS consulenza, (SELECT count(a5.id_tipo_contratto) FROM contratto a5 INNER JOIN tipo_contratto b5 ON a5.id_tipo_contratto = b5.id WHERE b5.descrizione LIKE \"Stage\" AND a5.id_tipo_azienda = b.id AND a5.attivo = 1) AS stage, (SELECT count(a6.id_tipo_contratto) FROM contratto a6 INNER JOIN tipo_contratto b6 ON a6.id_tipo_contratto = b6.id WHERE b6.descrizione LIKE \"PartitaIva\" AND a6.id_tipo_azienda = b.id AND a6.attivo = 1) AS partitaiva, (SELECT (indeterminati + determinati)/10) AS potenziale_stage, (SELECT potenziale_stage - stage) AS slot_stage, (SELECT indeterminati*0.66) AS potenziale_apprendistato, (SELECT potenziale_apprendistato-apprendistato) AS slot_apprendistato FROM contratto a INNER JOIN tipo_azienda b ON a.id_tipo_azienda = b.id WHERE a.attivo = 1 GROUP BY a.id_tipo_azienda";

	public final String SQL_DETTAGLIO_CONTRATTI = "SELECT c.* FROM contratto c INNER JOIN storico_contratti sc ON sc.id_contratto=c.id WHERE sc.id_anagrafica={0} AND c.id!=0 AND c.attivo=true";

	// SQL COMMESSE
	public final String SQL_DASHBOARD = "SELECT a.nominativo as Nominativo, f.descrizione as Tipo_Contratto, g.descrizione as Tipo_Azienda, h.descrizione as CCNL, a.data_inizio as Data_Inizio, a.data_fine as Data_Fine, e.mesi_durata as Mesi,e.livello_attuale as LivelloAttuale, e.livello_finale as LivelloFinale, e.data_assunzione as DataAssunzione FROM commessa a INNER JOIN storico_commesse b ON a.id = b.id_commessa INNER JOIN anagrafica c ON b.id_anagrafica = c.id INNER JOIN storico_contratti d ON c.id = d.id_anagrafica INNER JOIN contratto e ON d.id_contratto = e.id INNER JOIN tipo_contratto f ON e.id_tipo_contratto = f.id INNER JOIN tipo_azienda g ON e.id_tipo_azienda = g.id INNER JOIN tipo_ccnl h ON e.id_tipo_ccnl = h.id WHERE a.stato = 1 AND c.attivo = 1 AND e.attivo = 1";
	public final String SQL_LIST_COMMESSE = "SELECT DISTINCT a.id,a.nome,a.cognome,a.codice_fiscale,d.*,ta.descrizione FROM commessa d INNER JOIN storico_commesse sc ON d.id=sc.id_commessa INNER JOIN TipoAzienda ta ON ta.id = c.id_tipo_azienda INNER JOIN anagrafica a ON sc.id_anagrafica=a.id INNER JOIN storico_contratti scc ON sc.id_anagrafica=a.id INNER JOIN contratto c ON c.id=scc.id_contratto WHERE d.attivo=true and d.id != 0 AND c.id != 0 AND c.attivo=true";

	// SQL RUOLI-UTENTI-PROFILI
	public final String SQL_TREE_RUOLI = "SELECT a FROM Ruolo a WHERE 1 = 1 {0} ORDER BY a.nome";

	// SQL DETTAGLIO ANAGRAFICA DTO
	public final String SQL_DETTAGLIO_ANAGRAFICA_DTO = "SELECT a.*,c.*,d.*,e.*,f.*,g.*,h.*,i.*,n.* FROM anagrafica a INNER JOIN storico_contratti b ON a.id = b.id_anagrafica INNER JOIN contratto c ON b.id_contratto = c.id INNER JOIN tipo_livelli_contrattuali d ON d.id=c.id_tipo_livello INNER JOIN tipo_contratto e ON e.id=c.id_tipo_contratto INNER JOIN tipo_ccnl f ON f.id=c.id_tipo_ccnl INNER JOIN tipo_azienda g ON g.id=c.id_tipo_azienda INNER JOIN tipo_canale_reclutamento h ON h.id=c.id_tipo_canale_reclutamento LEFT OUTER JOIN tipo_causa_fine_rapporto i ON i.id=c.id_tipo_causa_fine_rapporto INNER JOIN utenti l ON a.id_utente=l.id INNER JOIN profili m ON m.id_utente =l.id INNER JOIN ruoli n ON m.id_ruolo=n.id WHERE a.id = {0} AND c.id=(select max(a1.id) from contratto a1 	inner join storico_contratti b on b.id_contratto=a1.id 	where b.id_anagrafica=a.id)";
	public final String SQL_DETTAGLIO_COMMESSA = "select c.*, ta.descrizione from commessa c inner join storico_commesse sc on sc.id_commessa=c.id inner join tipoAzienda ta on ta.id = c.id_tipo_azienda where sc.id_anagrafica={0} and c.id!=0 and c.attivo=true";
	// Possibile aggiunta alle funzioni di anagrafica ( query da modificare)
	// public final String SQL_RUOLO_PROFILO = "SELECT b.nome FROM profili a INNER
	// JOIN ruoli b ON a.id_ruolo = b.id INNER JOIN utenti c ON a.id_utente = c.id
	// WHERE c.id = :id ORDER BY b.nome";
	public final String SQL_GET_FUNZIONE_RUOLO = "SELECT a.id FROM funzioni a INNER JOIN privilegi b ON a.id = b.id_funzione INNER JOIN ruoli c ON b.id_ruolo = c.id WHERE 1=1 AND a.id_padre IS NULL {0}";

	public final String SQL_GET_PRIVILEGIO = "Select p FROM Privilegio p WHERE p.ruolo.id = :id_ruolo AND p.funzione.id = :id_funzione";
	public final String SQL_GET_PRIVILEGIO_ESISTENTE_PADRE = "SELECT 1 FROM Privilegi p WHERE p.funzione.id = :idpadre AND p.ruolo.id = :idruolo";
	public final String SQL_TREE_FUNZIONI = "SELECT a FROM Funzione a WHERE 1 = 1 {0} ORDER BY a.ordinamento";
	public final String SQL_GET_FUNZIONI_FIGLIE = "SELECT a FROM Funzione a WHERE a.funzione.id = id";
	public final String SQL_GET_RUOLO_UTENTE = "SELECT r.id FROM ruoli r INNER JOIN profili a ON r.id = a.id_ruolo INNER JOIN utenti u ON u.id = a.id_utente WHERE 1=1 AND u.username = '{0}'";
	public final String SQL_CURRENT_COMMESSA = "select * from commessa c where c.id=(select  c.id from storico_commesse st inner join anagrafica a on a.id=st.id_anagrafica inner join commessa c on c.id=st.id_commessa where c.stato=1 and a.id={0}";
	public final String SQL_CURRENT_CONTRATTO = "select * from contratto c where c.id=(select c.id from storico_contratti st inner join anagrafica a on a.id=st.id_anagrafica inner join contratto c  on c.id=st.id_contratto where c.attivo=1 and a.id={0})";
	public final String SQL_GET_STORICO_COMMESSE_BY_ID = "select c.* from commessa c inner join storico_commesse s on c.id=s.id_commessa where s.id_anagrafica={0} and c.id>0";

	public final String SQL_STORICO_CONTRATTI = "select c.*,tc.descrizione,tl.livello,tl.ccnl,tl.minimi_ret_23,ta.descrizione,tcn.descrizione,tcn.numero_mensilita,tcr.descrizione, tcfr.descrizione from contratto c inner join storico_contratti s  on c.id=s.id_contratto inner join tipo_contratto tc  on c.id_tipo_contratto=tc.id inner join tipo_livelli_contrattuali tl  on c.id_tipo_livello=tl.id inner join tipo_azienda ta  on c.id_tipo_azienda=ta.id inner join tipo_ccnl tcn  on c.id_tipo_ccnl=tcn.id inner join tipo_canale_reclutamento tcr on c.id_tipo_canale_reclutamento=tcr.id left outer join tipo_causa_fine_rapporto tcfr on c.id_tipo_causa_fine_rapporto=tcfr.id where s.id_anagrafica={0} and c.id>0";

	public final String SQL_GET_PROFILO = "select p.id from profili p inner join utenti u on u.id=p.id_utente where u.id=(select a.id_utente  from anagrafica a where a.id={0});";

	public final String SQL_ANAGRAFICA_DTO_CONTRATTI = "SELECT a.*, c.*, d.*, e.*, f.*, g.*, h.*, i.* FROM anagrafica a INNER JOIN storico_contratti b ON a.id = b.id_anagrafica INNER JOIN contratto c ON b.id_contratto = c.id INNER JOIN tipo_livelli_contrattuali d ON d.id=c.id_tipo_livello INNER JOIN tipo_contratto e ON e.id=c.id_tipo_contratto INNER JOIN tipo_ccnl f ON f.id=c.id_tipo_ccnl INNER JOIN tipo_azienda g ON g.id=a.id_tipo_azienda INNER JOIN tipo_canale_reclutamento h ON h.id=c.id_tipo_canale_reclutamento LEFT OUTER JOIN tipo_causa_fine_rapporto i ON i.id=c.id_tipo_causa_fine_rapporto WHERE c.id IN ({0})";

	public final String CONTRATTI_SCATTI_LIVELLO = "select c.id_contratto from contratti_scatti_livello c";

	public final String DELETE_CONTRATTI_SCATTI_LIVELLO = "DELETE FROM contratti_scatti_livello c WHERE c.id_contratto>0";

	public final String SQL_GET_ANAGRAFICA_BY_TOKEN = "SELECT a.*, d.*, e.*, b.id FROM anagrafica a INNER JOIN utenti b ON b.id=a.id_utente INNER JOIN profili c ON c.id_utente=b.id INNER JOIN ruoli d on c.id_ruolo=d.id LEFT OUTER JOIN tipo_azienda e ON e.id = a.id_tipo_azienda WHERE b.token_password LIKE '{0}'";

}
