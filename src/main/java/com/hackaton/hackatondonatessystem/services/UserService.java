package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.DonationUser;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.dto.DonationUserDTO;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<MemberDTO> findAll(){
        List<Member> members = repository.findAll();
        List<MemberDTO> membersDTO = members.stream().map(this::convertMembersDTO).collect(Collectors.toList());
        return membersDTO;
    }

   /* public MemberDTO find(Long id){
        Optional<Member> member = repository.findById(id);
        MemberDTO memberDTO = this.convertMembersDTO(member);
        return memberDTO;

    }*/
    public MemberDTO create(Member member){
        Member memberCreated = repository.save(member);
        MemberDTO memberDTO = new MemberDTO(memberCreated);
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
    private MemberDTO convertMembersDTO(Member member){
        return new MemberDTO(member);
    }
}
