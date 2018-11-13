//package cl.minsal.api.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class AuthConfiguration extends WebSecurityConfigurerAdapter {
//	
//	@Override
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//	    auth.inMemoryAuthentication()
//	        .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
//	        .and()
//	        .withUser("user").password(encoder().encode("userPass")).roles("USER");
//	}
//	 
//	@Bean
//	public PasswordEncoder  encoder() {
//	    return new BCryptPasswordEncoder();
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception { 
//	    http
//	    .csrf().disable()
//	    .exceptionHandling()
////	    .authenticationEntryPoint(restAuthenticationEntryPoint)
//	    .and()
//	    .authorizeRequests()
//	    .antMatchers("/api/**").authenticated()
//	    .antMatchers("/api/admin/**").hasRole("ADMIN")
//	    .and()
//	    .formLogin()
////	    .successHandler(mySuccessHandler)
////	    .failureHandler(myFailureHandler)
//	    .and()
//	    .logout();
//	}
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//      throws Exception {
//        auth
//          .inMemoryAuthentication()
//          .withUser("user")
//          .password("password")
//          .roles("USER");
//    }
// 
//    @Override
//    protected void configure(HttpSecurity http) 
//      throws Exception {
//        http.csrf().disable()
//          .authorizeRequests()
//          .antMatchers("/login").permitAll()
//          .anyRequest()
//          .authenticated()
//          .and()
//          .httpBasic();
//    }
//}

