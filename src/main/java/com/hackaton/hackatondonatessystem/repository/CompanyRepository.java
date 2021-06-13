package com.hackaton.hackatondonatessystem.repository;

import com.hackaton.hackatondonatessystem.domain.Cause;
import com.hackaton.hackatondonatessystem.domain.Company;
import com.hackaton.hackatondonatessystem.domain.Member;
import com.hackaton.hackatondonatessystem.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    List<Company> findCompaniesByCauses(Cause cause);

    Company findCompanyByEmail(String email);

    Company findCompanyByCnpj(String cnpj);

    List<Company> findCompaniesBySectors(Sector sector);

    List<Company> findCompanyBySectorsIsNotNull();

}
