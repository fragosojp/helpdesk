package com.fragoso.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fragoso.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
