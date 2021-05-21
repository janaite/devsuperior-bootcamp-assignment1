package net.janaite.devsuperior.bootcamp.assignment1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.janaite.devsuperior.bootcamp.assignment1.dto.ClientDTO;
import net.janaite.devsuperior.bootcamp.assignment1.entities.Client;
import net.janaite.devsuperior.bootcamp.assignment1.repositories.ClientRepository;
import net.janaite.devsuperior.bootcamp.assignment1.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoIntoEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	private void copyDtoIntoEntity(ClientDTO dto, Client entity) {
		entity.setId(null);
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
