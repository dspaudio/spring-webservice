package family.namkang.webservice.conf;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resources/static/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/news/create*").hasRole("USER") 
	        .anyRequest().permitAll();

		http
			.oauth2Login();
//			.defaultSuccessUrl("/loginSuccess")
//			.failureUrl("/loginFail")
//			.loginPage("/login");
	}

	@Bean
    public ClientRegistrationRepository clientRegistrationRepository( OAuth2ClientProperties oAuth2ClientProperties ) {
		List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
				.map(client -> getRegistration(oAuth2ClientProperties, client))
				.filter(Objects::nonNull).collect(Collectors.toList());
		
        return new InMemoryClientRegistrationRepository(registrations);
    }
	
	private ClientRegistration getRegistration(OAuth2ClientProperties oAuth2ClientProperties, String client) {

        if (client.equals("google")) {
    		OAuth2ClientProperties.Registration registration = oAuth2ClientProperties.getRegistration().get(client);
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(registration.getClientId()).clientSecret(registration.getClientSecret()).build();
        }
        if (client.equals("facebook")) {
    		OAuth2ClientProperties.Registration registration = oAuth2ClientProperties.getRegistration().get(client);
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(registration.getClientId()).clientSecret(registration.getClientSecret()).build();
        }
        

        if (client.equals("kakao")) {
    		OAuth2ClientProperties.Registration registration = oAuth2ClientProperties.getRegistration().get(client);
            return CustomOAuth2Provider.KAKAO.getBuilder(client)
                    .clientId(registration.getClientId()).clientSecret(registration.getClientSecret()).build();
        }
        
        return null;
    }
}
