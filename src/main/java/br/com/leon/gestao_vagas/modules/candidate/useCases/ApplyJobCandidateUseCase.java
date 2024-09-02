package br.com.leon.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leon.gestao_vagas.exceptions.JobNotFoundException;
import br.com.leon.gestao_vagas.exceptions.UserNotFoundException;
import br.com.leon.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.leon.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.leon.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.leon.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {

        this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        this.jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);

        ApplyJobEntity applyJobEntity = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        return applyJobRepository.save(applyJobEntity);
    }
}
