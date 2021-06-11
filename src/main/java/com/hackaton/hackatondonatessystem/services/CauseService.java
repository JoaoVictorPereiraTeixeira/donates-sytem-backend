package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.Cause;
import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.CompanyDonation;
import com.hackaton.hackatondonatessystem.domain.Sector;
import com.hackaton.hackatondonatessystem.dto.CauseDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.CompanyDonationDTO;
import com.hackaton.hackatondonatessystem.dto.SectorDTO;
import com.hackaton.hackatondonatessystem.repository.CauseRepository;
import com.hackaton.hackatondonatessystem.repository.CompanyDonationRepository;
import com.hackaton.hackatondonatessystem.repository.CompanyRepository;
import com.hackaton.hackatondonatessystem.repository.SectorRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CauseService {

    @Autowired
    CauseRepository repository;

    @Autowired
    CompanyDonationRepository companyDonationRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    CauseRepository causeRepository;

    public List<CauseDTO> findCausesBySector(Long id) throws NotFoundException {
        Sector sector = sectorRepository.findById(id).orElseThrow(() -> new NotFoundException("Sector not found"));
        List<Cause> causes = repository.findCausesBySector(sector);
        List<CauseDTO> causeDTO = causes.stream().map(this::convertCausesToDTO).collect(Collectors.toList());
        return causeDTO;
    }

    public List<CauseDTO> findAll(Cause cause){

        Example example = Example.of(cause, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<Cause> causes = repository.findAll(example);
        List<CauseDTO> causeDTO = causes.stream().map(this::convertCausesToDTO).collect(Collectors.toList());
        return causeDTO;
    }

    public CauseDTO create(Cause cause){
        Cause causeCreated = repository.save(cause);
        CauseDTO causeDTO = new CauseDTO(causeCreated);
        return causeDTO;
    }

    public void createCompanyDonation(CompanyDonation companyDonation) throws NotFoundException {

        Cause cause = causeRepository.findById(companyDonation.getCause().getId()).orElseThrow(() -> new NotFoundException("Cause not found"));
        Sector sector = sectorRepository.findById(companyDonation.getSector().getId()).orElseThrow(() -> new NotFoundException("Sector not found"));;

        Long companyId = companyDonation.getDonor().getId();
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Company not found"));
        company.addCauses(cause);
        company.addSectors(sector);

        companyRepository.save(company);

        companyDonationRepository.save(companyDonation);
    }

    public CauseDTO update(Cause cause){
        Cause causeUpdated = repository.save(cause);
        CauseDTO causeDTO = new CauseDTO(causeUpdated);
        return causeDTO;
    }

    public CauseDTO findById(Long id) throws Exception {
        Cause cause = repository.findById(id).orElseThrow(() -> new Exception("Cause not found"));
        CauseDTO causeDTO = new CauseDTO(cause);
        return causeDTO;
    }

    public List<CompanyDTO> findCompaniesByCause(Long id) throws Exception {
        Cause cause = repository.findById(id).orElseThrow(() -> new Exception("Cause not found"));
        List<Company> companies = companyRepository.findCompaniesByCauses(cause);
        List<CompanyDTO>  companiesDTO = companies.stream().map(this::convertCompaniesToDTO).collect(Collectors.toList());
        return companiesDTO;
    }


    public void delete(Long id){
        repository.deleteById(id);
    }

    private CauseDTO convertCausesToDTO(Cause cause){
        return new CauseDTO(cause);
    }

    private CompanyDTO convertCompaniesToDTO(Company company){
        return new CompanyDTO(company);
    }
}
