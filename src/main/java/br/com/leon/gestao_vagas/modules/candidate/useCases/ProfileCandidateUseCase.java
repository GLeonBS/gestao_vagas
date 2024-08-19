package br.com.leon.gestao_vagas.modules.candidate.useCases;

import br.com.leon.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.leon.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.leon.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        CandidateEntity candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        ProfileCandidateResponseDTO profile = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .build();

        return profile;

    }
}
