package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Permissao;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.repository.PermissionRepository;
import com.hackaton.hackatondonatessystem.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService{


    @Autowired
    UserRepository repository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder encoder;




    public List<MemberDTO> findAll(){
        List<Member> members = repository.findAll();
        List<MemberDTO> membersDTO = members.stream().map(this::convertMembersDTO).collect(Collectors.toList());
        return membersDTO;
    }

    public MemberDTO findById(Long id) throws Exception {
        Member member = repository.findById(id).orElseThrow(() -> new Exception("User not found"));
        MemberDTO memberDTO = new MemberDTO(member);
        return memberDTO;
    }

    public MemberDTO findByLogin(String login){
        Member member = repository.findByEmail(login);
        MemberDTO memberDTO = new MemberDTO(member);
        return memberDTO;
    }

    @Transactional
    public MemberDTO create(Member member) throws NotFoundException {
        String senhaCriptografada = encoder.encode(member.getPassword());
        member.setPassword(senhaCriptografada);

        Long id = Long.valueOf(1);
        Permissao permissao =  permissionRepository.findById(id).orElseThrow(() -> new NotFoundException("Permission not found"));
        member.setPermissoes(permissao);
        member = repository.save(member);
        MemberDTO memberDTO = new MemberDTO(member);
        return memberDTO;
    }

    public MemberDTO update(Member member){
        Member memberUpdated= repository.save(member);
        MemberDTO memberDTO = this.convertMembersDTO(memberUpdated);
        return memberDTO;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public UserDetails loadUserByUsername(String login)  {
        Member usuario = repository.findByEmail(login);

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

    public UserDetails autenticar(Member usuario) throws Exception {
        UserDetails userInDataBase = loadUserByUsername(usuario.getEmail());
        boolean passwordMatch = encoder.matches(usuario.getPassword(), userInDataBase.getPassword());

        if(passwordMatch){
            return userInDataBase;
        }

        throw new Exception("Password not matches");
    }

    private MemberDTO convertMembersDTO(Member member){
        return new MemberDTO(member);
    }
}
