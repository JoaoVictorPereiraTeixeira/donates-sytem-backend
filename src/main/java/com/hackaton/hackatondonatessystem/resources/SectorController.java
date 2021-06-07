package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.Cause;
import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import com.hackaton.hackatondonatessystem.services.CauseService;
import com.hackaton.hackatondonatessystem.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sectors")
public class SectorController {

    @Autowired
    SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<SectorDTO>> findAll(){
        List<SectorDTO> sectors = sectorService.findAll();
        return ResponseEntity.ok().body(sectors);
    }

}
