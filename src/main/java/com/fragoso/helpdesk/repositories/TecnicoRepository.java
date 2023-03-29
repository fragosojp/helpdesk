package com.fragoso.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fragoso.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
