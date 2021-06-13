package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.CompanyDonation;
import com.hackaton.hackatondonatessystem.domain.Sector;
import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDonationDTO;
import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import com.hackaton.hackatondonatessystem.services.CauseService;
import com.hackaton.hackatondonatessystem.services.CompanyService;
import com.hackaton.hackatondonatessystem.services.SectorService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/sectors")
public class SectorController {

    @Autowired
    SectorService sectorService;

    @Autowired
    CauseService causeService;

    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<SectorDTO>> findAll() throws NotFoundException {
        List<SectorDTO> sectors = sectorService.findAll();
        return ResponseEntity.ok().body(sectors);
    }

    @PostMapping
    public  ResponseEntity<SectorDTO> createSector(@RequestBody @Valid SectorDTO sectorDTO){
        Sector sector = new Sector(sectorDTO);
        SectorDTO sectorCreated = sectorService.create(sector);
        return ResponseEntity.ok().body(sectorCreated);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<SectorDTO> findById(@PathVariable("id") Long id) throws Exception {
        SectorDTO sectorDTO = sectorService.findById(id);
        return ResponseEntity.ok().body(sectorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorDTO> updateSector(@RequestBody @Valid SectorDTO sectorDTO, @PathVariable("id") Long id){
        Sector sector = new Sector(sectorDTO);
        sector.setId(id);
        SectorDTO sectorUpdated = sectorService.update(sector);
        return ResponseEntity.ok().body(sectorUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable("id") Long id){
        sectorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id-sector}/donor-companies")
    public  ResponseEntity<List<CompanyDTO>> findCompaniesBySector(@PathVariable("id-sector") Long id) throws Exception {
        List<CompanyDTO> companies = companyService.findCompaniesBySector(id);
        return ResponseEntity.ok().body(companies);
    }

    @GetMapping("/{id-sector}/causes")
    public ResponseEntity<List<CauseDTO>> findCausesOfSector(@PathVariable("id-sector") Long id) throws Exception {
        List<CauseDTO> causes = causeService.findCausesBySector(id);
        return ResponseEntity.ok().body(causes);
    }

}
