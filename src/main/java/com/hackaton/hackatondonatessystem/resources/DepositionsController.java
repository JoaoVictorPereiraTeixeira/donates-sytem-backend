package com.hackaton.hackatondonatessystem.resources;


import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.Deposition;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.DepositionDTO;
import com.hackaton.hackatondonatessystem.services.DepositionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/depositions")
public class DepositionsController {

    @Autowired
    DepositionService depositionService;

    @GetMapping
    public ResponseEntity<List<DepositionDTO>> findAll() throws NotFoundException {
        List<DepositionDTO> depositions = depositionService.findAll();
        return ResponseEntity.ok().body(depositions);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<DepositionDTO> findById(@PathVariable("id") Long id) throws NotFoundException {
        DepositionDTO deposition = depositionService.findById(id);
        return ResponseEntity.ok().body(deposition);
    }

    @PostMapping
    public  ResponseEntity<DepositionDTO> createDeposition(@RequestBody @Valid DepositionDTO depositionDTO) throws NotFoundException {
        Deposition deposition = new Deposition(depositionDTO);
        DepositionDTO depositionCreated = depositionService.create(deposition);
        return ResponseEntity.ok().body(depositionCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepositionDTO> updateDeposition(@RequestBody @Valid DepositionDTO depositionDTO, @PathVariable("id") Long id){
        Deposition deposition = new Deposition(depositionDTO);
        deposition.setId(id);
        DepositionDTO depositionUpdated = depositionService.update(deposition);
        return ResponseEntity.ok().body(depositionUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeposition(@PathVariable("id") Long id) throws NotFoundException {
        depositionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
