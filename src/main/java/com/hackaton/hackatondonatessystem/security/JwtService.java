package com.hackaton.hackatondonatessystem.security;

import com.hackaton.hackatondonatessystem.HackatonDonatessystemApplication;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    @Autowired
    private PasswordEncoder encoder;

    public JwtService() {
    }

    public String gerarToken(User user) {
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }



    public boolean tokenValido(String token){
        try{
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        }catch(Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token){
        return (String) obterClaims(token).getSubject();
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(HackatonDonatessystemApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        Member usuario = Member.builder().email("admin@gmail.com").build();
        String token = service.gerarToken(usuario);
        System.out.println(token);

        boolean isTokenValido = service.tokenValido(token);
        System.out.println("o token é válido? " + isTokenValido );

        System.out.println(service.obterLoginUsuario(token));


        String senha = "admin123";
        System.out.println(senha);
        String senhaEncodada = service.encoder.encode(senha);
        System.out.println(senhaEncodada);
        boolean match =  service.encoder.matches(senha, senhaEncodada);
        System.out.println(match);
    }
}