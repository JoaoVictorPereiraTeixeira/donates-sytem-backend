package com.hackaton.hackatondonatessystem.services;

import com.hackaton.hackatondonatessystem.domain.*;
import com.hackaton.hackatondonatessystem.dto.CompanyDTO;
import com.hackaton.hackatondonatessystem.dto.MemberDTO;
import com.hackaton.hackatondonatessystem.repository.CompanyRepository;
import com.hackaton.hackatondonatessystem.repository.PermissionRepository;
import com.hackaton.hackatondonatessystem.repository.SectorRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository repository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<CompanyDTO> findAll(){
        List<Company> companies = repository.findAll();
        List<CompanyDTO> companyDTO = companies.stream().map(this::convertCompaniesDTO).collect(Collectors.toList());
        return companyDTO;
    }

    public List<CompanyDTO> findOnlyDonorsCompanies() throws NotFoundException {
        List<Company> companies = repository.findCompanyBySectorsIsNotNull();

        ApiErrors.verifyListIsEmpty(companies,"There are no registered donors companies");

        List<CompanyDTO> companyDTO = companies.stream().map(this::convertCompaniesDTO).collect(Collectors.toList());
        return companyDTO;
    }

    public CompanyDTO findById(Long id) throws NotFoundException {
        Company company = repository.findById(id).orElseThrow(() -> new NotFoundException("Company not found"));
        CompanyDTO companyDTO = new CompanyDTO(company);
        return companyDTO;
    }

    public CompanyDTO create(Company company) throws NotFoundException {
        String senhaCriptografada = encoder.encode(company.getPassword());
        company.setPassword(senhaCriptografada);

        Long id = Long.valueOf(1);
        Permissao permissao =  permissionRepository.findById(id).orElseThrow(() -> new NotFoundException("Permission not found"));
        company.setPermissoes(permissao);

        Company companyCreated = repository.save(company);
        CompanyDTO companyDTO = new CompanyDTO(companyCreated);
        return companyDTO;
    }

    public CompanyDTO update(Company company){
        Company companyUpdated = repository.save(company);
        CompanyDTO companyDTO = convertCompaniesDTO(companyUpdated);
        return companyDTO;
    }

    public List<CompanyDTO> findCompaniesBySector(Long id) throws Exception {
        Sector sector = sectorRepository.findById(id).orElseThrow(() -> new Exception("Sector not found"));;
        List<Company> companies = repository.findCompaniesBySectors(sector);

        ApiErrors.verifyListIsEmpty(companies,"There are no registered companies in this sector");

        List<CompanyDTO> companiesDTO = companies.stream().map(this::convertCompaniesDTO).collect(Collectors.toList());
        return companiesDTO;
    }

    public CompanyDTO findByLogin(String login) throws NotFoundException {
        Company company = repository.findCompanyByEmail(login);
        if(company == null){
            company = repository.findCompanyByCnpj(login);
        }

        ApiErrors.verifyIsEmpty(company,"There are no registered company with this cnpj or email");

        CompanyDTO companyDTO = new CompanyDTO(company);
        return companyDTO;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }


    private CompanyDTO convertCompaniesDTO(Company company){
        return new CompanyDTO(company);
    }

    public String getPassword(CompanyDTO companyDTO) {
        Company company = repository.findCompanyByEmail(companyDTO.getEmail());
        return company.getPassword();
    }
}
