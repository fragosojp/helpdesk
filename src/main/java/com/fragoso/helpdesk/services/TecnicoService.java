package com.fragoso.helpdesk.services;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fragoso.helpdesk.domain.Pessoa;
import com.fragoso.helpdesk.domain.Tecnico;
import com.fragoso.helpdesk.domain.dtos.TecnicoDTO;
import com.fragoso.helpdesk.repositories.PessoaRepository;
import com.fragoso.helpdesk.repositories.TecnicoRepository;
import com.fragoso.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.fragoso.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! id: " + id));
	}

	public List<Tecnico> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}

	private void validaPorCpfEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
		
		Optional<Pessoa> obj_Cpf = pessoaRepository.findByCpf(objDTO.getCpf());	
		Optional<Pessoa> obj_Email = pessoaRepository.findByEmail(objDTO.getEmail());	
		
		if(obj_Cpf.isPresent() && obj_Cpf.get().getId() != objDTO.getId() && 
		   obj_Email.isPresent() && obj_Email.get().getId() != objDTO.getId() ) {
			throw new DataIntegrityViolationException("CPF E Email já cadastrado no sistema!");
		}
		
	}
}
