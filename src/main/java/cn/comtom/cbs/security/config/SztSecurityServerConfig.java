/**
 * 
 */
package cn.comtom.cbs.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author william
 *
 */
@Configuration
@EnableWebSecurity
public class SztSecurityServerConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private SpringSocialConfigurer socialConfig;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.apply(socialConfig);
//	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/dashboard/**");
        web.ignoring().antMatchers("/chart/config/**");
        web.ignoring().antMatchers("/**");
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
