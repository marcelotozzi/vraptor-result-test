package br.com.marcelotozzi.vraptorresults;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.marcelotozzi.vraptorresults.model.Evento;

public class Dado {
	public static Evento umEvento(Long id, String nome, String descricao,
			String local, Calendar data) {
		Evento ev = new Evento();
		ev.setId(id);
		ev.setNome(nome);
		ev.setDescricao(descricao);
		ev.setLocal(local);
		ev.setData(data);
		return ev;
	}
	
	public static List<Evento> umaListaCadastrada() {
		List<Evento> eventos = new ArrayList<Evento>();
		eventos.add(Dado.umEvento(1L, "BACONSP", "Bacon Conference SP", "Av Paulista", Calendar.getInstance()));
		eventos.add(Dado.umEvento(2L, "BrejasConf", "Conferencia de Brejas", "Av Brigadeiro Faria Lima", Calendar.getInstance()));
		return eventos;
	}
}
