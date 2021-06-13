package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.ApiErrors;
import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.User;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.CredenciaisDTO;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.dto.TokenDTO;
import com.hackaton.hackatondonatessystem.exceptions.CredentialsException;
import com.hackaton.hackatondonatessystem.security.JwtService;
import com.hackaton.hackatondonatessystem.services.CompanyService;
import com.hackaton.hackatondonatessystem.services.OAuthService;
import com.hackaton.hackatondonatessystem.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/oauth")
public class OAuthController {

    @Autowired
    OAuthService oauthService;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    JwtService jwtService;


    @PostMapping
    public TokenDTO autenticar(@RequestBody @Valid CredenciaisDTO credenciais) throws Exception {
        String type = oauthService.verifyType(credenciais.getLogin());
        if(type.equals("COMPANY")){
           return companyFlow(credenciais);
        } else if(type.equals("MEMBER")){
            return memberFlow(credenciais);
        } else {
            throw new CredentialsException("The field type must be COMPANY or MEMBER");
        }
    }


    private TokenDTO companyFlow(CredenciaisDTO credencials) throws Exception {
        String login = credencials.getLogin();
        CompanyDTO companyDTO = companyService.findByLogin(login);
        companyDTO.setPassword(credencials.getPassword());

        Company company = new Company(companyDTO);

        if(loginCompanyIsConfirmed(credencials.getLogin())){
            UserDetails usuarioAutenticado = oauthService.autenticar(company);
            String token = jwtService.gerarToken(company);
            return new TokenDTO(company.getEmail(),token);
        } else {
            throw new Exception("Login não confirmado");
        }
    }

    private boolean loginCompanyIsConfirmed(String login) throws NotFoundException {
        CompanyDTO company = companyService.findByLogin(login); //Repetido

        ApiErrors.verifyIsEmpty(company,"There is no company with this login");

        return !(company.getConfirmedEmail() == null || company.getConfirmedEmail() == false);
    }

    private TokenDTO memberFlow(CredenciaisDTO credencials) throws Exception {
        String login = credencials.getLogin();
        MemberDTO memberDTO = userService.findByLogin(login);
        memberDTO.setPassword(credencials.getPassword());

        Member member = new Member(memberDTO);

        if(loginMemberIsConfirmed(credencials.getLogin())){
            UserDetails usuarioAutenticado = oauthService.autenticar(member);
            String token = jwtService.gerarToken(member);
            return new TokenDTO(member.getEmail(),token);
        } else {
            throw new Exception("Login não confirmado");
        }
    }

    private boolean loginMemberIsConfirmed(String login) throws NotFoundException {
        MemberDTO member = userService.findByLogin(login);
        return !(member.getConfirmedEmail() == null || member.getConfirmedEmail() == false);
    }



}
