package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.ApiErrors;
import com.hackaton.hackatondonatessystem.domain.Sector;
import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import com.hackaton.hackatondonatessystem.repository.SectorRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorService {

    @Autowired
    SectorRepository repository;

    public List<SectorDTO> findAll() throws NotFoundException {
        List<Sector> sectors = repository.findAll();
        ApiErrors.verifyListIsEmpty(sectors,"There are no registered sectors");

        List<SectorDTO> sectorsDTO = sectors.stream().map(this::convertSectorsToDTO).collect(Collectors.toList());
        return sectorsDTO;
    }

    public SectorDTO findById(Long id) throws NotFoundException {
        Sector sector = repository.findById(id).orElseThrow(() -> new NotFoundException("Sector not found"));
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

    public void delete(Long id) throws NotFoundException {
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else{
            throw new NotFoundException("Cause not found");
        }
    }

    private SectorDTO convertSectorsToDTO(Sector sector){
        return new SectorDTO(sector);
    }
}
