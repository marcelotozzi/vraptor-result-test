package br.com.marcelotozzi.vraptorresults.controller;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.marcelotozzi.vraptorresults.Dado;
import br.com.marcelotozzi.vraptorresults.Entao;
import br.com.marcelotozzi.vraptorresults.dao.EventoDAO;
import br.com.marcelotozzi.vraptorresults.model.Evento;

public class EventoControllerTest {
	private EventoController controller;
	private Result result;
	@Mock
	private EventoDAO eventoDAO;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.result = new MockResult();
		this.controller = new EventoController(this.result, this.eventoDAO);
	}
	
	@Test
	public void deveRetornarUmEvento(){
		Evento evento = Dado.umEvento(1L, "BACONSP", "Bacon Conference SP", "Av Paulista", Calendar.getInstance());
		Mockito.when(this.eventoDAO.load(evento.getId())).thenReturn(evento);
		
		this.controller.show(evento.getId());
		
		Evento eventoRetornado = (Evento) this.result.included().get("evento");
		Entao.deveSerOMesmoEvento(evento, eventoRetornado);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void deveRetornarUmaListaDeEventos(){
		List<Evento> eventos = Dado.umaListaCadastrada();
		Mockito.when(this.eventoDAO.list()).thenReturn(eventos);
		
		this.controller.list();

		List<Evento> eventosRetornados = (List<Evento>) this.result.included().get("eventos");
		Entao.deveSerAMesmaLista(eventos,eventosRetornados);
	}
}