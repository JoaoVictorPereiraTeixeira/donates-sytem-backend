package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.Cause;
import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.services.CauseService;
import com.hackaton.hackatondonatessystem.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/causes")
public class CauseController {

    @Autowired
    CauseService causeService;

    @GetMapping
    public ResponseEntity<List<CauseDTO>> findAll(){
        List<CauseDTO> causes = causeService.findAll();
        return ResponseEntity.ok().body(causes);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CauseDTO> findById(@PathVariable("id") Long id) throws Exception {
        CauseDTO causeDTO = causeService.findById(id);
        return ResponseEntity.ok().body(causeDTO);
    }

    @PostMapping
    public  ResponseEntity<CauseDTO> createCause(@RequestBody CauseDTO causeDTO){
        Cause cause = new Cause(causeDTO);
        CauseDTO companyCreated = causeService.create(cause);
        return ResponseEntity.ok().body(companyCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id){
        causeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
