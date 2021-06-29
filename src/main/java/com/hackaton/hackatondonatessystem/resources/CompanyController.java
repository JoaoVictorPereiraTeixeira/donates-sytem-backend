package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.services.CompanyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAll(@RequestParam(value = "filter", required = false) String filter) throws NotFoundException {
        List<CompanyDTO> companies;

        if(filter != null && filter.matches("only_donors")) {
            companies = companyService.findOnlyDonorsCompanies();
        } else {
            companies = companyService.findAll();
        }

        return ResponseEntity.ok().body(companies);
    }


    @GetMapping("/{id}")
    public  ResponseEntity<CompanyDTO> findById(@PathVariable("id") Long id) throws Exception {
        CompanyDTO companyDTO = companyService.findById(id);
        return ResponseEntity.ok().body(companyDTO);
    }

    @PostMapping
    public  ResponseEntity<CompanyDTO> createCompany(@RequestBody @Valid CompanyDTO companyDTO) throws NotFoundException {
        Company company = new Company(companyDTO);
        CompanyDTO companyCreated = companyService.create(company);
        return ResponseEntity.ok().body(companyCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody @Valid CompanyDTO companyDTO, @PathVariable("id") Long id){
        Company company = new Company(companyDTO);
        company.setId(id);
        CompanyDTO companyUpdated = companyService.update(company);
        return ResponseEntity.ok().body(companyUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) throws NotFoundException {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
