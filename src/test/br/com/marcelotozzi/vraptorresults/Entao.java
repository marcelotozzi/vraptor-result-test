package br.com.marcelotozzi.vraptorresults;

import java.io.Writer;
import java.util.List;

import junit.framework.Assert;
import br.com.marcelotozzi.vraptorresults.model.Evento;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

public class Entao {
	private static final String DEFAULT_NEW_LINE = "";
	private static final char[] DEFAULT_LINE_INDENTER = {};
	private static final String INDENTED_NEW_LINE = "\n";
	private static final char[] INDENTED_LINE_INDENTER = { ' ', ' ' };

	private static boolean withoutRoot;
	private static boolean indented;
	
	public static void deveSerOMesmoEvento(Evento evento, Evento eventoRetornado) {
		Assert.assertEquals(evento.getId(), eventoRetornado.getId());
		Assert.assertEquals(evento.getNome(), eventoRetornado.getNome());
		Assert.assertEquals(evento.getDescricao(), eventoRetornado.getDescricao());
		Assert.assertEquals(evento.getLocal(), eventoRetornado.getLocal());
		Assert.assertEquals(evento.getData(), eventoRetornado.getData());
	}

	public static void deveSerAMesmaLista(List<Evento> eventos,
			List<Evento> eventosRetornados) {
		Assert.assertEquals(eventos.size(), eventosRetornados.size());
		Assert.assertTrue(eventos.containsAll(eventosRetornados));
	}
	
	public static String deveRetornaJSONde(Evento evento) {
		XStream xstream = getStreamJSON();

		xstream.alias("evento", Evento.class);
		return xstream.toXML(evento);
	}

	public static String deveRetornaListaJSONde(List<Evento> eventos) {
		XStream xstream = getStreamJSON();

		xstream.alias("eventos", List.class);
		return xstream.toXML(eventos);
	}

	private static XStream getStreamJSON() {
		final String newLine = (indented ? INDENTED_NEW_LINE : DEFAULT_NEW_LINE);
		final char[] lineIndenter = (indented ? INDENTED_LINE_INDENTER : DEFAULT_LINE_INDENTER);
		
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver(){
			public HierarchicalStreamWriter createWriter(Writer writer) {
				if (withoutRoot) {
					return new JsonWriter(writer, lineIndenter, newLine, JsonWriter.DROP_ROOT_MODE);
				}
				return new JsonWriter(writer, lineIndenter, newLine);
			}
		});
		return xstream;
	}

	public static String deveRetornaXMLde(Evento evento) {
		XStream xstream = new XStream();

		xstream.alias("evento", Evento.class);
		return xstream.toXML(evento);
	}

	public static String deveRetornaListaXMLde(List<Evento> eventos) {
		XStream xstream = new XStream();

		xstream.alias("eventos", List.class);
		xstream.alias("evento", Evento.class);
		return xstream.toXML(eventos);
	}
}