package com.hackaton.hackatondonatessystem.config;


import com.hackaton.hackatondonatessystem.security.JwtAuthFilter;
import com.hackaton.hackatondonatessystem.security.JwtService;
import com.hackaton.hackatondonatessystem.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@AllArgsConstructor
@NoArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private JwtService jwtService;



    //
//    public SecurityConfig(JwtService jwtServices) {
//        this.jwtService = jwtService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //eventos
                .antMatchers(HttpMethod.POST, "/users")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/oauth")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/eventos/{id}")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/voluntarios/{id-voluntario}/eventos")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/eventos/{id}/requisitos")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/eventos/{id}/voluntarios")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/requisitos/{id}")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/eventos/requisitos")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/eventos/sugestao/{id}")
                .hasAnyRole("ORGANIZADOR", "ADMIN")

                .antMatchers(HttpMethod.POST,"/mogi-guacu-consciente-api/v1/eventos")
                .hasAnyRole("ORGANIZADOR")
                .antMatchers(HttpMethod.POST,"/mogi-guacu-consciente-api/v1/eventos/{id}/voluntarios")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.POST,"/mogi-guacu-consciente-api/v1/eventos/{id}/requisitos")
                .hasAnyRole("ORGANIZADOR")
                .antMatchers(HttpMethod.PATCH,"/mogi-guacu-consciente-api/v1/eventos/{id-evento}")
                .hasAnyRole("ORGANIZADOR")
                .antMatchers(HttpMethod.DELETE,"/mogi-guacu-consciente-api/v1/eventos/{id-evento}")
                .hasAnyRole("ORGANIZADOR")
                .antMatchers(HttpMethod.DELETE,"/mogi-guacu-consciente-api/v1/eventos/{id-evento}/requisitos")
                .hasAnyRole("ORGANIZADOR")
                .antMatchers(HttpMethod.DELETE,"/mogi-guacu-consciente-api/v1/eventos/{id-evento}/voluntarios/{id-voluntario}")
                .hasAnyRole("VOLUNTARIO")

                //noticias
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/noticias")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/noticias/{id-noticia}")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.POST,"/mogi-guacu-consciente-api/v1/noticias")
                .hasAnyRole("ESCRITOR")
                .antMatchers(HttpMethod.DELETE,"/mogi-guacu-consciente-api/v1/noticias/{id-noticia}")
                .hasAnyRole("ESCRITOR")
                .antMatchers(HttpMethod.PATCH,"/mogi-guacu-consciente-api/v1/noticias/{id-news}")
                .hasAnyRole("ESCRITOR")


                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/feedbacks")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/feedbacks/{id}")
                .hasAnyRole("ORGANIZADOR", "ADMIN")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/eventos/{id-evento}/feedbacks")
                .hasAnyRole("ORGANIZADOR", "ADMIN")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/feedbacks/voluntarios/{id-voluntario}")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.POST,"/mogi-guacu-consciente-api/v1/feedbacks")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.DELETE,"/mogi-guacu-consciente-api/v1/feedbacks/{id-feedback}")
                .hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/noticias/{id-noticia}/comentarios")
                .hasAnyRole("WEB_APPLICATION","VOLUNTARIO")
                .antMatchers(HttpMethod.GET,"/mogi-guacu-consciente-api/v1/eventos/{id-evento}/comentarios")
                .hasAnyRole("WEB_APPLICATION","VOLUNTARIO")
                .antMatchers(HttpMethod.POST,"/mogi-guacu-consciente-api/v1/evento/{id-evento}/comentarios")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.POST,"/mogi-guacu-consciente-api/v1/comentarios/{id-comentario}/subcomentarios")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.PATCH,"/mogi-guacu-consciente-api/v1/eventos/{id-evento}/comentarios/{id-comentario}")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.DELETE,"/mogi-guacu-consciente-api/v1/comentarios/{id-comentario}")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.DELETE,"/mogi-guacu-consciente-api/v1/comentarios/{id-comentario}/subcomentarios/{id-subcomentario}")
                .hasAnyRole("VOLUNTARIO")

                //emails
                .antMatchers(HttpMethod.POST,"/mogi-guacu-consciente-api/v1/emails")
                .hasAnyRole( "ADMIN","WEB_APPLICATION")


                //usuarios
                .antMatchers(HttpMethod.POST, "/mogi-guacu-consciente-api/v1/usuario")
                //.hasAnyRole("WEB_APPLICATION")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/mogi-guacu-consciente-api/v1/usuario/usuario/{id-usuario}/confirmacao")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/mogi-guacu-consciente-api/v1/usuario/auth")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/mogi-guacu-consciente-api/v1/usuario/permissoes")
                .hasAnyRole("VOLUNTARIO")
                .antMatchers(HttpMethod.GET, "/mogi-guacu-consciente-api/v1/usuario/{id-usuario}")
                .hasAnyRole("ADMIN")



                .anyRequest().authenticated()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        http.cors();


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}