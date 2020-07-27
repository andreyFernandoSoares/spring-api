package br.com.alura.forum.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.forms.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	TopicoRepository topicoRepository;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		List<Topico> topicos = new ArrayList<>();
		
		if (nomeCurso == null)
			topicos = topicoRepository.findAll();
		else
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		
		return TopicoDto.converter(topicos);
	}
	
	@PostMapping
	public void cadastrar(@RequestBody TopicoForm topicoFrom) {
		Topico topico = topicoFrom.converter(cursoRepository);
		topicoRepository.save(topico);
	}
}
