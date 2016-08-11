package spittr.config;

import java.io.IOException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import spittr.data.DataConfigMarker;
import spittr.web.WebConfigMarker;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={WebConfigMarker.class, DataConfigMarker.class})
public class WebConfig extends WebMvcConfigurerAdapter {
	
/* BASIC view resolver:
*	@Bean
*	public ViewResolver viewResolver() {
*		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
*		resolver.setPrefix("/WEB-INF/view/");
*		resolver.setSuffix(".jsp");
*		resolver.setExposeContextBeansAsAttributes(true);
*		
*		return resolver;
*	}
*/
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	// THYMELEAF:
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine);
	    viewResolver.setCharacterEncoding("UTF-8");
	    return viewResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	    return templateEngine;
	}

	@Bean
	public TemplateResolver templateResolver() {
	    TemplateResolver templateResolver = new ServletContextTemplateResolver();
	    templateResolver.setPrefix("/WEB-INF/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setCharacterEncoding("UTF-8");
	    return templateResolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}
	
/* Apache Tiles layout resolver:
*	@Bean
*	public TilesConfigurer tilesConfigurer() {
*		TilesConfigurer tiles = new TilesConfigurer();
*		tiles.setDefinitions(new String[] {"/WEB-INF/layout/tiles.xml"});
*		tiles.setCheckRefresh(true);
*		return tiles;
*	}
*	
*	@Bean
*	public ViewResolver viewResolver() {
*		return new TilesViewResolver();
*	}
*/	

	//MULTIPART RESOLVER:
	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		return new StandardServletMultipartResolver();
	}
	
}
