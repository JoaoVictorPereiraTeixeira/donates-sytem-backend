package com.hackaton.hackatondonatessystem.repository;

import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector,Long> {

}
