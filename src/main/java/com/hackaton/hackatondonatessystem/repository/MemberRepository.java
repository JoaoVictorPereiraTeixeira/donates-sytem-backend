package com.hackaton.hackatondonatessystem.repository;

import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);

    Member findByCpf(String cpf);

    Boolean existsUserByEmail(String email);

}
