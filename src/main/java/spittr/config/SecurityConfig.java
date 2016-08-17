package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// authentication (in-memory)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("spitter").password("spitter_password").roles("SPITTER")
			.and()
			.withUser("admin").password("admin_password").roles("SPITTER","ADMIN");
	}

	// authorizing requests (disables default login page from previous configure() )
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		// bring back default login page:
		.formLogin()
		.and()
		.authorizeRequests()
			.antMatchers("/spitters/me").hasRole("SPITTER")
//			.antMatchers("/spitters/me").authenticated()
			.antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
//			.antMatchers(HttpMethod.POST, "/spittles").authenticated()
			.anyRequest().permitAll()
		.and()
		//require https for registration form:
		.requiresChannel()
			.antMatchers("spitter/register").requiresSecure()
		//require http for homepage:
			.antMatchers("/").requiresInsecure();
	}

	
}
