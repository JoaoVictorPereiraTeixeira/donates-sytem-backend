package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OAuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MemberRepository memberRepository;

    public UserDetails autenticar(Member usuario) throws Exception {
        UserDetails userInDataBase = loadUserByUsername(usuario.getEmail());
        boolean passwordMatch = encoder.matches(usuario.getPassword(), userInDataBase.getPassword());

        if(passwordMatch){
            return userInDataBase;
        }

        throw new Exception("Password not matches");
    }

    public UserDetails loadUserByUsername(String login)  {
        Member usuario = memberRepository.findByEmail(login);

        List<String> rolesList = new ArrayList<>();

        if(usuario.getPermissoes().isAdmin()){
            rolesList.add("ADMIN");
        }

        if(usuario.getPermissoes().isComumUser()){
            rolesList.add("COMUM_USER");
        }

        if(usuario.getPermissoes().isOrganizer()){
            rolesList.add("ORGANIZER");
        }

        if(usuario.getPermissoes().isWebApplication()){
            rolesList.add("WEB_APPLICATION");
        }

        String[] roles = rolesList.toArray(new String[0]);

        Member member = new Member();
        member.setEmail(usuario.getEmail());
        member.setPassword(usuario.getPassword());

        return User
                .builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(roles)
                .build();
    }

}
