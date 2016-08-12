package spittr.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	
	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	
	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
			@RequestParam(value="count", defaultValue="20") int count) {
		return spittleRepository.findSpittles(max, count);
	}
	
	//when name of placeholder is the same as handler method parameter name (spittleId),
	//value parameter for @PathVariable can be omitted: ...@Pathvariable long spittleId...
	@RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		Spittle spittle = spittleRepository.findOne(spittleId);
		if ( spittle == null ) {
			throw new SpittleNotFoundException();
		}
		model.addAttribute(spittle);
		return "spittle";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveSpittle(SpittleForm form, Model model) {
		spittleRepository.save(new Spittle(form.getMessage(), new Date(), 
				form.getLatitude(), form.getLongitude()));
		return "redirect:/spittles";
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public String spittles(Model model) {
//		model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//		return "spittles";
//	}
	
//	*********explicit key for model:*********
//		@RequestMapping(method=RequestMethod.GET)
//	public String spittles(Model model) {
//	  model.addAttribute("spittleList",
//	      spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//	  return "spittles";
//	}
	
//	*********non-Spring:*********
//		@RequestMapping(method=RequestMethod.GET)
//	public String spittles(Map model) {
//	  model.put("spittleList",
//	          spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//	  return "spittles";
//	}
	
//	*********all-implicit:*********
//	@RequestMapping(method=RequestMethod.GET)
//	public List<Spittle> spittles() {
//	  return spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//	}
	

}
