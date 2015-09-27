package eu.accesa.crowdfund.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
			authBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMINISTRATOR");
			authBuilder.inMemoryAuthentication().withUser("consultant").password("consultant").roles("CONSULTANT");
			authBuilder.inMemoryAuthentication().withUser("client").password("client").roles("CLIENT");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
		.antMatchers("/home**").access("hasAnyRole('ADMINISTRATOR','CONSULTANT')")
		.antMatchers("/faq**").access("hasAnyRole('ADMINISTRATOR','CONSULTANT')")
		.and().formLogin().loginPage("/login").failureUrl("/login?loginError=true")
		.and().exceptionHandling().accessDeniedPage("/denied")
		.and().logout().logoutUrl("/logout").and().csrf().disable();
	}
}
