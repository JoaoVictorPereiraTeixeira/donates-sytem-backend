package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAll(){
        List<MemberDTO> users = userService.findAll();

        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public  ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO){
        Member member = new Member(memberDTO);
        MemberDTO memberCreated = userService.create(member);
        return ResponseEntity.ok().body(memberCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO, @PathVariable("id") Long id){
        Member member = new Member(memberDTO);
        member.setId(id);
        MemberDTO memberUpdated = userService.update(member);
        return ResponseEntity.ok().body(memberUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
