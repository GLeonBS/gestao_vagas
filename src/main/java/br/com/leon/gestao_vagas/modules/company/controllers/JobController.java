package br.com.leon.gestao_vagas.modules.company.controllers;

import br.com.leon.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.leon.gestao_vagas.modules.company.entities.JobEntity;
import br.com.leon.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(@Valid @RequestBody CreateJobDTO dto, HttpServletRequest httpServletRequest) {
        Object companyId = httpServletRequest.getAttribute("company_id");

        JobEntity jobEntity = JobEntity.builder()
                .description(dto.getDescription())
                .benefits(dto.getBenefits())
                .level(dto.getLevel())
                .companyId(UUID.fromString(companyId.toString()))
                .build();

        return this.createJobUseCase.execute(jobEntity);
    }
}
