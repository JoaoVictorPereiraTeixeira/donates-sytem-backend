package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Permissao;
import com.hackaton.hackatondonatessystem.repository.CompanyRepository;
import com.hackaton.hackatondonatessystem.repository.MemberRepository;
import com.hackaton.hackatondonatessystem.repository.UserRepository;
import javassist.NotFoundException;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public UserDetails autenticar(com.hackaton.hackatondonatessystem.domain.User usuario) throws Exception {
        UserDetails userInDataBase = loadUserByUsername(usuario.getEmail());
        boolean passwordMatch = encoder.matches(usuario.getPassword(), userInDataBase.getPassword());

        if(passwordMatch){
            return userInDataBase;
        }

        throw new Exception("Password not matches");
    }


    public UserDetails loadUserByUsername(String login)  {
        com.hackaton.hackatondonatessystem.domain.User user = userRepository.findByEmail(login);
        UserDetails userDetails = loadUser(user);
        return userDetails;
    }


    public UserDetails loadUser(com.hackaton.hackatondonatessystem.domain.User user){

        List<String> rolesList = new ArrayList<>();

        if(user.getPermissoes().isAdmin()){
            rolesList.add("ADMIN");
        }

        if(user.getPermissoes().isComumUser()){
            rolesList.add("COMUM_USER");
        }

        if(user.getPermissoes().isOrganizer()){
            rolesList.add("ORGANIZER");
        }

        if(user.getPermissoes().isWebApplication()){
            rolesList.add("WEB_APPLICATION");
        }

        String[] roles = rolesList.toArray(new String[0]);

        return User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }


    public String verifyType(String login) throws NotFoundException {
        Company company = companyRepository.findCompanyByEmail(login);
        if(company != null){
            return "COMPANY";
        }

        company = companyRepository.findCompanyByCnpj(login);
        if(company != null){
            return "COMPANY";
        }

        Member member = memberRepository.findByEmail(login);
        if(member != null){
            return "MEMBER";
        }

        member = memberRepository.findByCpf(login);
        if(member != null){
            return "MEMBER";
        }

        throw new NotFoundException("Login not found");

    }
}
