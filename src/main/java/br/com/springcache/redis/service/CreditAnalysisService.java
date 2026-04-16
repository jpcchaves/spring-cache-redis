package br.com.springcache.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreditAnalysisService {

    public void getCreditAnalysis(String cpf) {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            log.error("An error occurred while doing credit analysis for cpf: {}", cpf, ex);
        }
    }
}
