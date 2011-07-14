package br.com.marcelotozzi.vraptorresults.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.marcelotozzi.vraptorresults.Dado;
import br.com.marcelotozzi.vraptorresults.Entao;
import br.com.marcelotozzi.vraptorresults.dao.EventoDAO;
import br.com.marcelotozzi.vraptorresults.model.Evento;

public class SerializeEventoControllerTest {

	private MockSerializationResult result;
	private EventoController controller;
	@Mock
	private EventoDAO eventoDAO;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.result = new MockSerializationResult();
		this.controller = new EventoController(this.result, this.eventoDAO);
	}
	
	@Test
	public void deveRetornarUmEventoNoFormatoJSON() throws Exception {
		Evento evento = Dado.umEvento(1L, "BACONSP", "Bacon Conference SP", "Av Paulista", Calendar.getInstance());
		Mockito.when(this.eventoDAO.load(evento.getId())).thenReturn(evento);
		
		this.controller.showJSON(1L);
		
		String esperado = Entao.deveRetornaJSONde(evento);
		String retornado = this.result.serializedResult();
		
		Assert.assertThat(retornado, is(equalTo(esperado)));
	}
	
	@Test
	public void deveRetornarUmaListaDeEventoNoFormatoJSON() throws Exception {
		List<Evento> eventos = Dado.umaListaCadastrada();
		Mockito.when(this.eventoDAO.list()).thenReturn(eventos);
		
		this.controller.listJSON();
		
		String esperado = Entao.deveRetornaListaJSONde(eventos);
		String retornado = this.result.serializedResult();
		
		Assert.assertThat(retornado , is(equalTo(esperado)));
	}
	
	@Test
	public void deveRetornarUmEventoNoFormatoXML() throws Exception {
		Evento evento = Dado.umEvento(1L, "BACONSP", "Bacon Conference SP", "Av Paulista", Calendar.getInstance());
		Mockito.when(this.eventoDAO.load(evento.getId())).thenReturn(evento);
		
		this.controller.showXML(1L);
		
		String esperado = Entao.deveRetornaXMLde(evento);
		String retornado = this.result.serializedResult();
		
		Assert.assertThat(retornado, is(equalTo(esperado)));
	}
	
	@Test
	public void deveRetornarUmaListaDeEventoNoFormatoXML() throws Exception {
		List<Evento> eventos = Dado.umaListaCadastrada();
		Mockito.when(this.eventoDAO.list()).thenReturn(eventos);
		
		this.controller.listXML();
		
		String esperado = Entao.deveRetornaListaXMLde(eventos);
		String retornado = this.result.serializedResult();
		
		Assert.assertThat(retornado , is(equalTo(esperado)));
	}
}