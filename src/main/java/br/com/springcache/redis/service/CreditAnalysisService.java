package br.com.springcache.redis.service;

import br.com.springcache.redis.dto.CreditAnalysisResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditAnalysisService {

    private final static String KEY_PREFIX = "analise-credito:";

    private final RedisService redisService;

    public CreditAnalysisResponseDTO getCreditAnalysis(String cpf) {
        String redisKey = getRedisKey(cpf);

        return redisService.getValue(redisKey, CreditAnalysisResponseDTO.class)
                .orElseGet(() -> processAndCacheCreditAnalysis(cpf, redisKey));
    }

    private CreditAnalysisResponseDTO processAndCacheCreditAnalysis(String cpf, String redisKey) {
        CreditAnalysisResponseDTO creditAnalysis;
        try {
            Thread.sleep(30000);
            creditAnalysis = getCreditAnalysisResponseDTO(true, cpf);
        } catch (Exception ex) {
            log.error("An error occurred while doing credit analysis for cpf: {}", cpf, ex);
            creditAnalysis = getCreditAnalysisResponseDTO(false, cpf);
        }

        redisService.setValueWithTTL(redisKey, creditAnalysis, Duration.ofHours(1));

        return creditAnalysis;
    }

    private String getRedisKey(String cpf) {
        return KEY_PREFIX + cpf;
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
