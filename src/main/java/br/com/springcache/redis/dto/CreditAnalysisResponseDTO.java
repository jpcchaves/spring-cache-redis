package br.com.springcache.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditAnalysisResponseDTO {

    private Boolean success;
    private String message;
    private String cpf;
    private LocalDateTime analysisTimestamp;
}
