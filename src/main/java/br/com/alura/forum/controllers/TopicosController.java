package br.com.alura.forum.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired
	TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos")
	public List<TopicoDto> lista(String nomeCurso) {
		List<Topico> topicos = new ArrayList<>();
		
		if (nomeCurso == null)
			topicos = topicoRepository.findAll();
		else
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		
		return TopicoDto.converter(topicos);
	}
}
