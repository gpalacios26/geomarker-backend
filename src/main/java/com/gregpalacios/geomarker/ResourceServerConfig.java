package com.gregpalacios.geomarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

//Segunda Clase
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
    private ResourceServerTokenServices tokenServices;
	
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
                http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()             
                .antMatchers("/api/login/**" ).permitAll()
                .antMatchers("/api/roles/**" ).authenticated()
                .antMatchers("/api/usuarios/**" ).authenticated()
                .antMatchers("/api/capas/icono/**" ).permitAll()
                .antMatchers("/api/capas/**" ).authenticated()
                .antMatchers("/api/ubicacion/**" ).authenticated()
                .antMatchers("/api/tokens/anular/**" ).permitAll()
                .antMatchers("/api/tokens/**" ).authenticated();
    }

}