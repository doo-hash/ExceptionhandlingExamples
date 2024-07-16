package com.example.SpringExceptionHandling.ExceptionhandlingExamples.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.SpringExceptionHandling.ExceptionhandlingExamples.exceptions.ClientNotFoundException;
import com.example.SpringExceptionHandling.ExceptionhandlingExamples.model.Client;
import com.example.SpringExceptionHandling.ExceptionhandlingExamples.model.ClientJson;
import com.example.SpringExceptionHandling.ExceptionhandlingExamples.repository.ClientRepository;
import com.example.SpringExceptionHandling.ExceptionhandlingExamples.service.ClientService;

@Controller
public class FirstClientController {

	private static final Logger logger = LoggerFactory.getLogger(FirstClientController.class);

	@Autowired
	private ClientService clientservice;
	@Autowired
	private ClientRepository clientrepo;
	
	@GetMapping("/clients")
	@ResponseBody
	public ResponseEntity<List<Client>> getAllClients(){
		List<Client> clients = clientrepo.getAllIClients();
		return new ResponseEntity<List<Client>>(clients,HttpStatus.OK);
	}
	
	@GetMapping("/clients/{id}")
	@ResponseBody
	public ResponseEntity<Client> getClient(Long id) throws Exception{
		if(id == null || id<=0) {
			throw new ClientNotFoundException(id);
		}
		Client client = clientrepo.getClient(id);
		return new ResponseEntity<Client>(client,HttpStatus.OK);
	}

	@PostMapping(value = "/addclient"
			,consumes = {"application/json"},
			produces = {"application/json"}
	)
	public ResponseEntity<Client> saveClient(@RequestBody Client client
			,UriComponentsBuilder builder){
		clientrepo.saveClient(client);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/addclient/{id}").buildAndExpand(client.getId()).toUri());
		return new ResponseEntity<Client>(client,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateclient")
	@ResponseBody
	public ResponseEntity<Client> updateClient(@RequestBody Client client){
		if(client != null) {
			clientrepo.updateClient(client);
		}
		return new ResponseEntity<Client>(client,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) throws Exception{
		clientrepo.deleteClient(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/jsonclients/{id}")
	@ResponseBody
	public ResponseEntity<ClientJson> getClientjson(@PathVariable Long id) throws Exception {

		if(id == 1) {
			throw new ClientNotFoundException(id);
		}else if(id == 2) {
			throw new SQLException("SQLException,id : "+id);
		}else if(id == 3) {
			throw new IOException("IOException, id : "+id);	
		}
		else if(id == 4) {
			throw new NullPointerException("NullPointerException, id : "+id);	
		}else if(id == 10) {
			ClientJson client = new ClientJson();
			client.setId(id);
			client.setName("soujanya");
			clientservice.saveClient(client);
			logger.info("client saved");
			return new ResponseEntity<>(client,HttpStatus.OK);
		}else {
			throw new Exception("Generic Exception, id : "+id);
		}
	}
	
}
