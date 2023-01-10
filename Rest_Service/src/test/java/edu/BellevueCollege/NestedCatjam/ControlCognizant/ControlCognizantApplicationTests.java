package edu.BellevueCollege.NestedCatjam.ControlCognizant;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.json.JSONArray;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
class ControlCognizantApplicationTests {

	@Test
	void contextLoads() {
	}



	@Autowired
	private MockMvc mvc;

	@Test
	void retrieveAllUsers() throws Exception {
		var request = get("/users");
		var result = mvc.perform(request).andExpect(status().isOk())
				.andExpect(checkName(0, "Graham Priest"))
				.andExpect(checkName(1, "Bertrand Russell"))
				.andExpect(checkName(2, "Haskell Curry")).andReturn();

	}

	@BeforeAll
	void setUp() throws Exception {
		postUser(
				"""
                {
                    "id": null,
                    "name": "Graham Priest",
                    "email": "gp@email.com",
                    "posts": []
                }
                """
		);

		postUser(
				"""
                {
                    "id": null,
                    "name": "Bertrand Russell",
                    "email": "br@email.com",
                    "posts": []
                }
                """
		);

		postUser(
				"""
                        {
                            "id": null,
                            "name": "Haskell Curry",
                            "email": "hc@email.com",
                            "posts": []
                        }
                     """
		);
	}
	@Test
	void getUserById() throws Exception {



		var results =
			mvc.perform(get("/users"))

			.andReturn();
		String response = results.getResponse().getContentAsString();
		var documentContext = JsonPath.parse(response);
		var id = documentContext.read("$[0].id");
		var name = documentContext.read("$[0].name");
		var email = documentContext.read("$[0].email");
		mvc.perform(get("/users/" + id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(name))
				.andExpect(jsonPath("$.email").value(email)).andReturn();
	}

	@Test
	public void deleteUser() throws Exception {
		var users = mvc.perform(get("/users")).andReturn();
		String response = users.getResponse().getContentAsString();
		System.out.println(response);
		DocumentContext documentContext = JsonPath.parse(response);
		var id = documentContext.read("$[0].id");
		var email = documentContext.read("$[0].email");
		mvc.perform(delete("/users/" + id)).andExpect(status().isOk());

		var usersWithTheFirstUserDeleted = mvc.perform(get("/users")).andReturn().getResponse().getContentAsString();
		System.out.println(usersWithTheFirstUserDeleted);
		var json = JsonPath.parse(usersWithTheFirstUserDeleted);
		assertNotEquals(id, json.read("$[0].id"));
		assertNotEquals(email, json.read("$[0].email"));


	}

	@Test
	public void getPosts() throws Exception {
		var userWithPostsJson =
          """
		  {
                    "id": null,
                    "name": "Kurt Friedrich Gödel",
                    "email": "gödel@email.com",
                    "posts": [{ "id" : null, "content": "Test post 0" }, { "id": null, "content": "Test post 1" }, {"id": null, "content": "Test post 2"}]
      	  }
		  """;
		postUser(
          userWithPostsJson
		);

		var users = mvc.perform(get("/users")).andReturn().getResponse();
		var json = users.getContentAsString();

		var mapper = new ObjectMapper();
		var usersJson = mapper.readTree(json);
		assert usersJson.isArray();
		var success = false;
		var usersWithPostsJsonNode = mapper.readTree(userWithPostsJson);
		String id = null;
		for (JsonNode node : usersJson) {
			System.out.println(node.toPrettyString());
			if (node.get("name").asText().startsWith("Kurt")) { id = node.get("id").asText(); break; }
		}
		assertNotNull(id);

		var user = mvc.perform(get("/users/" + id)).andReturn().getResponse().getContentAsString();
		System.out.println(user);




	}

	private ResultMatcher checkName(int at, String name) {
		return jsonPath("$[" + at + "].name").value(name);
	}
	private void postUser(String json) throws Exception {
		assertEquals(200,
				mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json)).andReturn().getResponse().getStatus());
	}

}