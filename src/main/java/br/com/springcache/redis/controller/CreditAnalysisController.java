package br.com.springcache.redis.controller;

import br.com.springcache.redis.dto.CreditAnalysisResponseDTO;
import br.com.springcache.redis.service.CreditAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit-analysis")
public class CreditAnalysisController {

    private final CreditAnalysisService creditAnalysisService;

    @GetMapping("/{cpf}'")
    public ResponseEntity<CreditAnalysisResponseDTO> getCreditAnalysis(@PathVariable String cpf) {
        log.info("Doing credit analysis for cpf: {}", cpf);
        var response = creditAnalysisService.getCreditAnalysis(cpf);
        log.info("Finished credit analysis with response: {}", response);
        return ResponseEntity.ok(response);
    }
}
