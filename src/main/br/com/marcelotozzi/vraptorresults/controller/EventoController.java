package br.com.marcelotozzi.vraptorresults.controller;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.marcelotozzi.vraptorresults.dao.EventoDAO;

public class EventoController {
	private Result result;
	private EventoDAO eventoDAO;

	public EventoController(Result result, EventoDAO eventoDAO) {
		this.result = result;
		this.eventoDAO = eventoDAO;
	}

	public void list() {
		this.result.include("eventos", this.eventoDAO.list());
	}
	
	public void listXML() {
		this.result.use(Results.xml()).from(this.eventoDAO.list(), "eventos").serialize();
	}
	
	public void listJSON() {
		this.result.use(Results.json()).from(this.eventoDAO.list(), "eventos").serialize();
	}

	public void show(Long id) {
		this.result.include("evento", this.eventoDAO.load(id));
	}

	public void showXML(Long id) {
		this.result.use(Results.xml()).from(this.eventoDAO.load(id), "evento").serialize();	
	}

	public void showJSON(Long id) {
		this.result.use(Results.json()).from(this.eventoDAO.load(id), "evento").serialize();
	}
}