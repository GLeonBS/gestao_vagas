package br.com.leon.gestao_vagas.modules.candidate.useCases;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.leon.gestao_vagas.exceptions.JobNotFoundException;
import br.com.leon.gestao_vagas.exceptions.UserNotFoundException;
import br.com.leon.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.leon.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.leon.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.leon.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.leon.gestao_vagas.modules.company.entities.JobEntity;
import br.com.leon.gestao_vagas.modules.company.repositories.JobRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    void shouldNotBeAbleToApplyJobWithCandidateNotFound(){
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    void shouldNotBeAbleToApplyJobWithJobNotFound(){
        UUID idCandidate = UUID.randomUUID();
        CandidateEntity candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    void shouldBeAbleToCreateANewApplyJob() {
        UUID idCandidate = UUID.randomUUID();
        UUID idJob = UUID.randomUUID();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        ApplyJobEntity applyJob = ApplyJobEntity
                .builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        ApplyJobEntity applyJobCreated = ApplyJobEntity
                .builder()
                .id(UUID.randomUUID())
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        ApplyJobEntity result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result.getId()).isNotNull();
    }
}
