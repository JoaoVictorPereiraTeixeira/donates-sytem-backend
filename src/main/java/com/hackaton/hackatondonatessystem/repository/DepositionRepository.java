package com.hackaton.hackatondonatessystem.repository;

import com.hackaton.hackatondonatessystem.domain.Deposition;
import com.hackaton.hackatondonatessystem.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositionRepository extends JpaRepository<Deposition,Long> {

}
