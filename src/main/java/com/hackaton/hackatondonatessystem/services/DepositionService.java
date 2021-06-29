package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.ApiErrors;
import com.hackaton.hackatondonatessystem.domain.Deposition;
import com.hackaton.hackatondonatessystem.domain.Sector;
import com.hackaton.hackatondonatessystem.dto.DepositionDTO;
import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import com.hackaton.hackatondonatessystem.repository.DepositionRepository;
import com.hackaton.hackatondonatessystem.repository.SectorRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepositionService {

    @Autowired
    DepositionRepository repository;

    public List<DepositionDTO> findAll() throws NotFoundException {
        List<Deposition> depositions = repository.findAll();
        ApiErrors.verifyListIsEmpty(depositions,"There are no registered depositions");

        List<DepositionDTO> depositionsDTO = depositions.stream().map(this::convertDepositionToDTO).collect(Collectors.toList());
        return depositionsDTO;
    }

    public DepositionDTO findById(Long id) throws NotFoundException {
        Deposition deposition = repository.findById(id).orElseThrow(() -> new NotFoundException("Deposition not found"));
        DepositionDTO depositionDTO = new DepositionDTO(deposition);
        return depositionDTO;
    }

    public DepositionDTO create(Deposition deposition){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        deposition.setDate(dateFormat.format(date));

        Deposition depositionCreated = repository.save(deposition);
        DepositionDTO depositionDTO = new DepositionDTO(depositionCreated);
        return depositionDTO;
    }

    public DepositionDTO update(Deposition deposition){
        Deposition depositionUpdated = repository.save(deposition);
        DepositionDTO depositionDTO = new DepositionDTO(depositionUpdated);
        return depositionDTO;
    }

    public void delete(Long id) throws NotFoundException {
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else{
            throw new NotFoundException("Deposition not found");
        }
    }

    private DepositionDTO convertDepositionToDTO(Deposition deposition){
        return new DepositionDTO(deposition);
    }
}
