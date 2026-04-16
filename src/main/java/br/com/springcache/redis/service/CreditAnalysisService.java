package br.com.springcache.redis.service;

import br.com.springcache.redis.dto.CreditAnalysisResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CreditAnalysisService {

    public CreditAnalysisResponseDTO getCreditAnalysis(String cpf) {
        try {
            Thread.sleep(30000);
            return getCreditAnalysisResponseDTO(true, cpf);
        } catch (Exception ex) {
            log.error("An error occurred while doing credit analysis for cpf: {}", cpf, ex);
            return getCreditAnalysisResponseDTO(false, cpf);
        }
    }

    private CreditAnalysisResponseDTO getCreditAnalysisResponseDTO(Boolean success, String cpf) {
        return CreditAnalysisResponseDTO.builder()
                .success(success)
                .analysisTimestamp(LocalDateTime.now())
                .cpf(cpf)
                .message("Credit analysis finished successfully!")
                .build();
    }
}
