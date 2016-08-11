package spittr.web;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.Spittle;
import spittr.data.SpittleRepository;

public class SpittleIdTest {

	@Test
	public void testSpittle() throws Exception {
		
		Spittle expectedSpittle = new Spittle("Hello", new Date());
		SpittleRepository mockRepo = mock(SpittleRepository.class);
		when(mockRepo.findOne(12345)).thenReturn(expectedSpittle);
		
		SpittleController controller = new SpittleController(mockRepo);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spittles/12345"))
			.andExpect(view().name("spittle"))
			.andExpect(model().attributeExists("spittle"))
			.andExpect(model().attribute("spittle", expectedSpittle));
		
	}

}
