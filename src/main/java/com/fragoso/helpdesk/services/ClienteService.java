package com.fragoso.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fragoso.helpdesk.domain.Pessoa;
import com.fragoso.helpdesk.domain.Cliente;
import com.fragoso.helpdesk.domain.dtos.ClienteDTO;
import com.fragoso.helpdesk.repositories.PessoaRepository;
import com.fragoso.helpdesk.repositories.ClienteRepository;
import com.fragoso.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.fragoso.helpdesk.services.exceptions.ObjectnotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! id: " + id));
	}

	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	public Cliente upadate(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
		
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size()> 0) {
			throw new DataIntegrityViolationException("Cliente posssui ordens de serviços e nao pode ser deletado!");
		} else {
			repository.deleteById(id);
		}
	}
	
	private void validaPorCpfEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
		
//		Optional<Pessoa> obj_Cpf = pessoaRepository.findByCpf(objDTO.getCpf());	
//		Optional<Pessoa> obj_Email = pessoaRepository.findByEmail(objDTO.getEmail());	
//		
//		if(obj_Cpf.isPresent() && obj_Cpf.get().getId() != objDTO.getId() && 
//		   obj_Email.isPresent() && obj_Email.get().getId() != objDTO.getId() ) {
//			throw new DataIntegrityViolationException("CPF E Email já cadastrado no sistema!");
//		}
		
	}


}
