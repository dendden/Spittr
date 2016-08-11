package spittr.web;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.data.SpitterRepository;

public class RegistrationFromTest {
	
	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterRepository mockRepo = mock(SpitterRepository.class);
		SpitterController controller = new SpitterController(mockRepo);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	}
	
}
