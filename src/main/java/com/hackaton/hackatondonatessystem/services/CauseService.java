package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.Cause;
import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.repository.CauseRepository;
import com.hackaton.hackatondonatessystem.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CauseService {

    @Autowired
    CauseRepository repository;

    public List<CauseDTO> findAll(){
        List<Cause> causes = repository.findAll();
        List<CauseDTO> causeDTO = causes.stream().map(this::convertCausesToDTO).collect(Collectors.toList());
        return causeDTO;
    }

    public CauseDTO create(Cause cause){
        Cause causeCreated = repository.save(cause);
        CauseDTO causeDTO = new CauseDTO(causeCreated);
        return causeDTO;
    }

    public CauseDTO findById(Long id) throws Exception {
        Cause cause = repository.findById(id).orElseThrow(() -> new Exception("Cause not found"));
        CauseDTO causeDTO = new CauseDTO(cause);
        return causeDTO;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private CauseDTO convertCausesToDTO(Cause cause){
        return new CauseDTO(cause);
    }
}
