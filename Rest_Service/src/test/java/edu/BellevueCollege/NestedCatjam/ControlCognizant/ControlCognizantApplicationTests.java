package edu.BellevueCollege.NestedCatjam.ControlCognizant;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hamcrest.*;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
class ControlCognizantApplicationTests {

	@Test
	void contextLoads() {
	}

	@Value("${env.TESTING_ACCESS_TOKEN}")
	private String testingAccessToken;

	@Autowired
	private MockMvc mvc;


	private String[] categories = {"Identify: Asset Management (ID.AM)",
				"Identify: Governance (ID.GV)",
						"Identify: Business Environment (ID.BE)",
						"Identify: Risk Assessment (ID.RA)",
						"Identify: Risk Management Strategy (ID.RM)",
						"Identify: Supply Chain Risk Management (ID.SC)",
						"Protect: Identity Management and Access Control (PR.AC)",
						"Protect: Awareness and Training (PR.AT)",
						"Protect: Data Security (PR.DS)",
						"Protect: Information Protection Processes and Procedures (PR.IP)",
						"Protect: Maintenance (PR.MA)",
						"Protect: Protective Technology (PR.PT)",
						"Detect: Anomalies and Events (DE.AE)",
						"Detect: Security Continuous Monitoring (DE.CM)",
						"Detect: Detection Processes (DE.DP)",
						"Respond: Communications (RS.CO)",
						"Respond: Mitigation (RS.MI)",
						"Respond: Analysis (RS.AN)",
						"Respond: Improvements (RS.IM)",
						"Respond: Response Planning (RS.RP)",
						"Recover Communications (RC.CO)",
						"Recover: Improvements (RC.IM)",
						"Recover: Recovery Planning (RC.RP)" };
	@Test
	public void testGetAllNistControlCategoriesHasAllCategories() throws Exception {
		mvc.perform(get("/api/v1/categories").header("Authorization", "Bearer " + testingAccessToken)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$", hasSize(23))).andExpect(jsonPath("$[*]", containsInAnyOrder(categories)));
	}

	





}
