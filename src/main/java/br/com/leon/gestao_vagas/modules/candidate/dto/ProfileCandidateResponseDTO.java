package br.com.leon.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor Java", required = true)
    private String description;

    @Schema(example = "Leon")
    private String username;

    @Schema(example = "Leon@gmail.com")
    private String email;
    private UUID id;

    @Schema(example = "Gabriel Leon")
    private String name;
}
