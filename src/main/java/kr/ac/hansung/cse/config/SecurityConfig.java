package kr.ac.hansung.cse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//config에 대한 클래스라는것을 알려줌
@Configuration
//webSecurity를 enable하겠다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {//WebSecurityConfigurerAdapter상속
 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http        
        	.authorizeRequests() 
        		.anyRequest()//어떤 요청이든
        		.permitAll()//다 허락하겠다. -> 다 허용하겠다.
        		.and()
            .csrf().disable(); //csrf기능을 사용하지않겠다.
    }
}
