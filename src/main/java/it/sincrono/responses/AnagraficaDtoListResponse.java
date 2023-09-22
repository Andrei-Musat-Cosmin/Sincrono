package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.AnagraficaDto;

public class AnagraficaDtoListResponse extends GenericResponse {
	private List<AnagraficaDto> list;

	public AnagraficaDtoListResponse(Esito esito, List<AnagraficaDto> list) {
		super(esito);
		this.list = list;
	}

	public AnagraficaDtoListResponse() {
	}

	public List<AnagraficaDto> getList() {
		return list;
	}

	public void setList(List<AnagraficaDto> list) {
		this.list = list;
	}

}