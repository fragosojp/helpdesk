package com.fragoso.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fragoso.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
