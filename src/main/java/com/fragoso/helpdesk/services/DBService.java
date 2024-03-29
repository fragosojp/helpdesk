package com.fragoso.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fragoso.helpdesk.domain.Chamado;
import com.fragoso.helpdesk.domain.Cliente;
import com.fragoso.helpdesk.domain.Tecnico;
import com.fragoso.helpdesk.domain.enums.Perfil;
import com.fragoso.helpdesk.domain.enums.Prioridade;
import com.fragoso.helpdesk.domain.enums.Status;
import com.fragoso.helpdesk.repositories.ChamadoRepository;
import com.fragoso.helpdesk.repositories.ClienteRepository;
import com.fragoso.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository TecnicoRepository;
	@Autowired
	private ClienteRepository ClienteRepository;
	@Autowired
	private ChamadoRepository ChamadoRepository;
	
	public void instaciaDB() {
		Tecnico tec1 = new Tecnico(null, "Antonio Santos","126.009.710-41", "joaotec@email.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Cliente 2 Teste", "426.009.710-123", "cliente@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		TecnicoRepository.saveAll(Arrays.asList(tec1));
		ClienteRepository.saveAll(Arrays.asList(cli1));
		ChamadoRepository.saveAll(Arrays.asList(c1));
	}
}
