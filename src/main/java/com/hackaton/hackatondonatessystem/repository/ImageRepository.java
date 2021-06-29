package com.hackaton.hackatondonatessystem.repository;

import com.hackaton.hackatondonatessystem.domain.Image;
import com.hackaton.hackatondonatessystem.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

}
