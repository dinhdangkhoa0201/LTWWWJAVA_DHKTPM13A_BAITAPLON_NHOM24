package fit.se.main.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import fit.se.main.principal.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/", "/sign-in", "/sign-out").permitAll();

//		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')");
//
//		http.authorizeRequests().antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests().and().formLogin()
		.loginPage("/sign-in")
		.loginProcessingUrl("/j_spring_security_check")
		.defaultSuccessUrl("/index")
		.failureUrl("/sign-in?error=false")
		.usernameParameter("email")
		.passwordParameter("password")
		
		.and().logout()
		.logoutUrl("/sign-out")
		.logoutSuccessUrl("/sign-in");
		
		http.authorizeRequests().and()
		.rememberMe().tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(1*24*60*60);
		
	}
	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		return null;
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//	}
}
