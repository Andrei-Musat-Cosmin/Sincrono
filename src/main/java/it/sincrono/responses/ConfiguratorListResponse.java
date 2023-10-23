package it.sincrono.responses;

import java.util.List;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Configurator;

public class ConfiguratorListResponse extends GenericResponse {
	private List<Configurator> list;

	public ConfiguratorListResponse(Esito esito, List<Configurator> list) {
		super(esito);
		this.list = list;
	}

	public ConfiguratorListResponse() {
	}

	public List<Configurator> getList() {
		return list;
	}

	public void setList(List<Configurator> list) {
		this.list = list;
	}

}