package com.aydsii.tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aydsii.tp2.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}