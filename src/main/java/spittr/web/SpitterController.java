package spittr.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	private SpitterRepository spitterRepository;
	
	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}
	
	//WITHOUT SESSION FLASH ATTRIBUTES:
//	@RequestMapping(value="/register", method=RequestMethod.POST)
//	public String processRegistraionForm(@RequestPart("profilePicture") MultipartFile profilePicture, 
//			@Valid Spitter spitter, Errors errors, Model model) throws IllegalStateException, IOException {
//		if ( errors.hasErrors() ) {
//			List<ObjectError> errorsList = errors.getAllErrors();
//			for ( ObjectError oe : errorsList ) {
//				System.out.println(oe.toString());
//			}
//			return "registerForm";
//		}
//		spitterRepository.save(spitter);
//		model.addAttribute("username", spitter.getUsername());
//		profilePicture.transferTo(new File("/data/spittr/" + profilePicture.getOriginalFilename()));
//		
//		//naive and dangerous concatenation without model usage:
//		//return "redirect:/spitter/" + spitter.getUsername();
//		
//		//using model placeholder in redirect:
//		return "redirect:/spitter/{username}";
//	}
//	
//	@RequestMapping(value="/{username}", method=RequestMethod.GET)
//	public String showSpitterProfile(@PathVariable String username, Model model) {
//		Spitter spitter = spitterRepository.findByUsername(username);
//		model.addAttribute(spitter);
//		return "profile";
//	}
	//*************************************************************
	
	//WITH REDIRECT SESSION FLASH ATTRIBUTES:
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistraionForm(@RequestPart("profilePicture") MultipartFile profilePicture, 
			@Valid Spitter spitter, Errors errors, RedirectAttributes model) throws IllegalStateException, IOException {
		if ( errors.hasErrors() ) {
			List<ObjectError> errorsList = errors.getAllErrors();
			for ( ObjectError oe : errorsList ) {
				System.out.println(oe.toString());
			}
			return "registerForm";
		}
		spitterRepository.save(spitter);
		model.addAttribute("username", spitter.getUsername());
		model.addFlashAttribute(spitter);
		//explicit key naming:
		//model.addFlashAttribute("spitter", spitter);
		profilePicture.transferTo(new File("/data/spittr/" + profilePicture.getOriginalFilename()));

		return "redirect:/spitter/{username}";
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		if ( !model.containsAttribute("spitter") ) {
			Spitter spitter = spitterRepository.findByUsername(username);
			model.addAttribute(spitter);
		}
		
		return "profile";
	}

}
