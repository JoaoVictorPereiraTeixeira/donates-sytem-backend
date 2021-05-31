package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.repository.CompanyRepository;
import com.hackaton.hackatondonatessystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository repository;

    public List<CompanyDTO> findAll(){
        List<Company> companies = repository.findAll();
        List<CompanyDTO> companyDTO = companies.stream().map(this::convertCompaniesDTO).collect(Collectors.toList());
        return companyDTO;
    }

    public CompanyDTO create(Company company){
        Company companyCreated = repository.save(company);
        CompanyDTO companyDTO = new CompanyDTO(companyCreated);
        return companyDTO;
    }
    
    private CompanyDTO convertCompaniesDTO(Company company){
        return new CompanyDTO(company);
    }
}
