package com.fragoso.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fragoso.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
