package br.com.aplicacao.demo.security.config;

import br.com.aplicacao.demo.services.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Random;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
     SecurityFilter securityFilter;

    @Bean
    public Random random () {
        return new Random();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/autenticar").authenticated()
                        .requestMatchers(HttpMethod.POST, "/usuario/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/aa").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/auth/carro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produto/anunciar").authenticated()
                        .requestMatchers(HttpMethod.GET, "/usuario/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/listagem/{pagina}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/de/{idUsuario}/{pagina}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/teste").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/{pagina}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/id/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/subcategorias/{categoria}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/categorias").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/subcategoria/{subCategoria}/{pagina}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/categoria/{categoria}/{pagina}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/conjunto/categoria/aleatoria").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produto/anunciar/lista").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/produto/desativar/{id}").authenticated()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
