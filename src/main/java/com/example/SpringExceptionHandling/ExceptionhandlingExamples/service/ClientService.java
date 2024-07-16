package com.example.SpringExceptionHandling.ExceptionhandlingExamples.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringExceptionHandling.ExceptionhandlingExamples.model.ClientJson;
import com.example.SpringExceptionHandling.ExceptionhandlingExamples.repository.ClientJsonRepository;

@Service
public class ClientService {

	@Autowired
	private ClientJsonRepository clientrepo;
	
	public ClientJson saveClient(ClientJson client) {
		clientrepo.save(client);
		return clientrepo.findById(client.getId()).get();
	}
	
	public ClientJson getClient(Long id) {
		ClientJson client = clientrepo.findById(id).get();
		return client;
	}
}
