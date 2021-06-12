package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.User;
import com.hackaton.hackatondonatessystem.dto.CredenciaisDTO;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.dto.TokenDTO;
import com.hackaton.hackatondonatessystem.security.JwtService;
import com.hackaton.hackatondonatessystem.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/oauth")
public class OAuthController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;


    @PostMapping
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) throws Exception {
        try{
            Member user = Member.builder()
                    .email(credenciais.getLogin())
                    .password(credenciais.getSenha()).build();

            if(loginConfirmed(credenciais)){
                UserDetails usuarioAutenticado = userService.autenticar(user);
                String token = jwtService.gerarToken(user);
                return new TokenDTO(user.getEmail(),token);
            } else {
                throw new Exception("Login não confirmado");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean loginConfirmed(CredenciaisDTO credenciais) throws NotFoundException {
        MemberDTO member = userService.findByLogin(credenciais.getLogin());
        if(member.getConfirmedEmail() == null || member.getConfirmedEmail() == false){
            return false;
        } else {
            return true;
        }
    }
}
