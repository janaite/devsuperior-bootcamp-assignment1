package net.janaite.devsuperior.bootcamp.assignment1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.janaite.devsuperior.bootcamp.assignment1.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
