//package spittr.web;
//
//import static org.mockito.Mockito.atLeastOnce;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
//import org.junit.Test;
//import org.springframework.test.web.servlet.MockMvc;
//
//import spittr.Spitter;
//import spittr.data.SpitterRepository;
//import spittr.web.SpitterController;
//
//public class ProcessRegistrationTest {
//	
//	@Test
//	public void shouldProcessRegistration() throws Exception {
//		SpitterRepository mockRepo = mock(SpitterRepository.class);
//		System.out.println("About to crerate 2 spitters...");
//		Spitter unsaved = new Spitter("jbauer","24hours","jbauer@24.com","Jack","Bauer");
//		Spitter saved = new Spitter(24L,"jbauer","24hours","jbauer@24.com","Jack","Bauer");
//		System.out.println("Created 2 spitters!");
//		when(mockRepo.save(unsaved)).thenReturn(saved);
//		
//		SpitterController controller = new SpitterController(mockRepo);
//		MockMvc mockMvc = standaloneSetup(controller).build();
//		
//		mockMvc.perform(post("/spitter/register")
//			.param("firstName", "Jack")
//			.param("lastName", "Bauer")
//			.param("username", "jbauer")
//			.param("password", "24hours"))
//			.andExpect(redirectedUrl("/spitter/jbauer"));
//		
//		verify(mockRepo, atLeastOnce()).save(unsaved);
//		
//	}
//
//}
