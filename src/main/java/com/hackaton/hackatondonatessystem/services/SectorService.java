package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Sector;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import com.hackaton.hackatondonatessystem.repository.SectorRepository;
import com.hackaton.hackatondonatessystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorService {

    @Autowired
    SectorRepository repository;

    public List<SectorDTO> findAll(){
        List<Sector> sectors = repository.findAll();
        List<SectorDTO> sectorsDTO = sectors.stream().map(this::convertSectorsToDTO).collect(Collectors.toList());
        return sectorsDTO;
    }

    private SectorDTO convertSectorsToDTO(Sector sector){
        return new SectorDTO(sector);
    }
}
