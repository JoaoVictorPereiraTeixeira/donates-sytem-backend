package com.hackaton.hackatondonatessystem.resources;

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
@RequestMapping(value = "/companies")
public class CauseController {

    @Autowired
    CauseService causeService;

    @GetMapping
    public ResponseEntity<List<CauseDTO>> findAll(){
        List<CauseDTO> causes = causeService.findAll();
        return ResponseEntity.ok().body(causes);
    }

}
