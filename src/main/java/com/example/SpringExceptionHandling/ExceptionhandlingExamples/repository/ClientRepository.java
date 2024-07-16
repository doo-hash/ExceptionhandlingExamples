package com.example.SpringExceptionHandling.ExceptionhandlingExamples.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.SpringExceptionHandling.ExceptionhandlingExamples.exceptions.ClientNotFoundException;
import com.example.SpringExceptionHandling.ExceptionhandlingExamples.model.Client;

@Repository
public class ClientRepository {

	@Autowired
	JdbcTemplate template;
	
	public List<Client> getAllIClients(){
		List<Client> clients = template.query(
				"select id,name,email,age from client",
				(result,rowNum)->new Client(result.getLong("id"),
				result.getString("name"),
				result.getString("email"),
				result.getInt("age")));
		return clients;
	}

	public Client getClient(Long id) throws Exception {
		Client client = null;
		String query = "SELECT * FROM CLIENT WHERE ID=?";
		try {
			client = template.queryForObject(query,
					new Object[]{id},
					new BeanPropertyRowMapper<>(Client.class));
		}catch(Exception e) {
			throw new ClientNotFoundException(id);
		}
		return client;
	}
	
	public int saveClient(Client client) {
		String query = "INSERT INTO CLIENT VALUES(?,?,?,?)";
		return template.update(query,new Object[] {
				Long.valueOf(client.getId()),
				String.valueOf(client.getName()),
				String.valueOf(client.getEmail()),
				Integer.valueOf(client.getAge())
		});
	}
	
	public int deleteClient(Long id) throws Exception {
		String query = "DELETE FROM CLIENT WHERE ID=?";
		int size = template.update(query, id);
		if(size == 0) {
			throw new ClientNotFoundException(id);
		}
		return size;
	}
	
	public void updateClient(Client client) {
		String query = "UPDATE CLIENT SET NAME=?,EMAIL=?,AGE=? WHERE ID=?";
		template.update(query,new Object[] {
				Long.valueOf(client.getId()),
				client.getName(),client.getEmail(),
				client.getAge()
		});
	}
}
