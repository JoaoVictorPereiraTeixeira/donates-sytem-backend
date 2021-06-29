package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.Cause;
import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.CompanyDonation;
import com.hackaton.hackatondonatessystem.domain.Sector;
import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDonationDTO;
import com.hackaton.hackatondonatessystem.services.CauseService;
import com.hackaton.hackatondonatessystem.services.CompanyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/causes")
public class CauseController {

    @Autowired
    CauseService causeService;

    @Autowired
    CauseService sectorService;

    @GetMapping
    public ResponseEntity<List<CauseDTO>> findAll(
            @RequestParam(value = "sectorId", required = false) String sectorId,
            @RequestParam(value = "title", required = false) String title) throws Exception {

        Cause causeFilter = new Cause();
        Sector sector = new Sector();

        if(sectorId != null){
            Long id =  Long.valueOf(sectorId);
            sector.setId(id);
        }

        causeFilter.setSector(sector);
        causeFilter.setTitle(title);

        List<CauseDTO> causes = causeService.findAll(causeFilter);
        return ResponseEntity.ok().body(causes);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CauseDTO> findById(@PathVariable("id") Long id) throws Exception {
        CauseDTO causeDTO = causeService.findById(id);
        return ResponseEntity.ok().body(causeDTO);
    }


    @GetMapping("/{id}/donor-companies")
    public  ResponseEntity<List<CompanyDTO>> findCompaniesByCause(@PathVariable("id") Long id) throws Exception {
        List<CompanyDTO> companies = causeService.findCompaniesByCause(id);
        return ResponseEntity.ok().body(companies);
    }

    @PostMapping("/{id}/company-donations")
    public  ResponseEntity<Void> createCompanyDonation(@PathVariable("id") Long id, @RequestBody @Valid CompanyDonationDTO companyDonationDTO) throws Exception {
        CauseDTO causeDTO = new CauseDTO();
        causeDTO.setId(id);
        companyDonationDTO.setCause(causeDTO);

        CompanyDonation companyDonation = new CompanyDonation(companyDonationDTO);
        causeService.createCompanyDonation(companyDonation);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public  ResponseEntity<CauseDTO> createCause(@RequestBody @Valid CauseDTO causeDTO){
        Cause cause = new Cause(causeDTO);
        CauseDTO causeCreated = causeService.create(cause);
        return ResponseEntity.ok().body(causeCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CauseDTO> updateCause(@RequestBody @Valid CauseDTO causeDTO, @PathVariable("id") Long id){
        Cause cause = new Cause(causeDTO);
        cause.setId(id);
        CauseDTO causeCreated = causeService.update(cause);
        return ResponseEntity.ok().body(causeCreated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) throws NotFoundException {
        causeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
