package net.janaite.devsuperior.bootcamp.assignment1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.janaite.devsuperior.bootcamp.assignment1.dto.ClientDTO;
import net.janaite.devsuperior.bootcamp.assignment1.entities.Client;
import net.janaite.devsuperior.bootcamp.assignment1.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}

}
