package it.sincrono.repositories.dto;
import java.util.List;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;



public class AnagraficaDto {
	
	private String nome;
	
	private String cognome;
	
	private Contratto contratto;
	
	private Commessa commessa;

	public AnagraficaDto(String nome, String cognome, Contratto contratto, Commessa commessa) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.contratto = contratto;
		this.commessa = commessa;
	}

	public AnagraficaDto() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}
	
	
	
	
	
}
