package com.moretech.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moretech.map.schemas.TaskListRequest;
import com.moretech.map.utils.EndPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class MapApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {

		String json = new ObjectMapper().writeValueAsString(new TaskListRequest("56.800584, 60.675637", true, true, true));

		this.mockMvc.perform(post(EndPoint.api + EndPoint.check)
						.contentType("application/json")
						.content(json))
				.andDo(print())
				.andExpect(status().isOk());
	}

}


