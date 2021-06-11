package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.Cause;
import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Sector;
import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
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

    public SectorDTO findById(Long id) throws Exception {
        Sector sector = repository.findById(id).orElseThrow(() -> new Exception("Sector not found"));
        SectorDTO sectorDTO = new SectorDTO(sector);
        return sectorDTO;
    }

    public SectorDTO create(Sector sector){
        Sector sectorCreated = repository.save(sector);
        SectorDTO sectorDTO = new SectorDTO(sectorCreated);
        return sectorDTO;
    }

    public SectorDTO update(Sector sector){
        Sector sectorUpdated = repository.save(sector);
        SectorDTO sectorDTO = new SectorDTO(sectorUpdated);
        return sectorDTO;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private SectorDTO convertSectorsToDTO(Sector sector){
        return new SectorDTO(sector);
    }
}
