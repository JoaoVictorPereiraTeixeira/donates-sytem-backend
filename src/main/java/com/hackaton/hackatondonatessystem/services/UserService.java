package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.ApiErrors;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Permissao;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.repository.PermissionRepository;
import com.hackaton.hackatondonatessystem.repository.MemberRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService{

    @Autowired
    MemberRepository repository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder encoder;


    public List<MemberDTO> findAll() throws NotFoundException {
        List<Member> members = repository.findAll();

        ApiErrors.verifyListIsEmpty(members,"There are no registered members");
        List<MemberDTO> membersDTO = members.stream().map(this::convertMembersDTO).collect(Collectors.toList());
        return membersDTO;
    }

    public MemberDTO findById(Long id) throws NotFoundException {
        Member member = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        MemberDTO memberDTO = new MemberDTO(member);
        return memberDTO;
    }

    public MemberDTO findByLogin(String login) throws NotFoundException {
        Member member = repository.findByEmail(login);
        ApiErrors.verifyIsEmpty(member,"There are no registered member");

        MemberDTO memberDTO = new MemberDTO(member);
        return memberDTO;
    }

    @Transactional
    public MemberDTO create(@Valid Member member) throws NotFoundException {
        String senhaCriptografada = encoder.encode(member.getPassword());
        member.setPassword(senhaCriptografada);

        Long id = Long.valueOf(1);
        Permissao permissao =  permissionRepository.findById(id).orElseThrow(() -> new NotFoundException("Permission not found"));
        member.setPermissoes(permissao);
        member = repository.save(member);
        MemberDTO memberDTO = new MemberDTO(member);
        return memberDTO;
    }

    public MemberDTO update(Member member) {
        Member memberUpdated= repository.save(member);
        MemberDTO memberDTO = this.convertMembersDTO(memberUpdated);
        return memberDTO;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }


    private MemberDTO convertMembersDTO(Member member){
        return new MemberDTO(member);
    }
}
