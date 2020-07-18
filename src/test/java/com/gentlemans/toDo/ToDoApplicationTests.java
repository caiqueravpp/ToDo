package com.gentlemans.toDo;

import com.gentlemans.toDo.model.ToDoModel;
import javafx.application.Application;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import sun.management.AgentConfigurationError;

@Getter
@Setter
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl(){
		return "http://localhost:" + port;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllNotes(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/notes", HttpMethod.GET, entity, String.class);
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetNotesById(){
		ToDoModel toDoModel = restTemplate.getForObject(getRootUrl() + "/notes/1", ToDoModel.class);
		System.out.println(toDoModel.getTitle());
		Assert.assertNotNull(toDoModel);
	}

	@Test
	public void testCreateNote{
		ToDoModel toDoModel = new ToDoModel();
		toDoModel.setTitle("Teste");
		toDoModel.setDescription("Testando com JUnit a API de notas");

		ResponseEntity<ToDoModel> postResponse	= restTemplate.postForEntity(getRootUrl() + "/notes", toDoModel, ToDoModel.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePost(){
		int id = 1;
		ToDoModel toDoModel = restTemplate.getForObject(getRootUrl() + "/notes" + id, ToDoModel.class);
		toDoModel.setTitle("Alteração");
		toDoModel.setDescription("Testando Update");

		restTemplate.put(getRootUrl() + "/notes" + id, toDoModel);

		ToDoModel updatedNote = restTemplate.getForObject(getRootUrl() + "/notes" + id, ToDoModel.class);
		Assert.assertNotNull(updatedNote);
	}

}
