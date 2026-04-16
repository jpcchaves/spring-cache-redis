package br.com.springcache.redis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public <T> void setValueWithTTL(String key, T value, Duration ttl) {
        log.info("Setting value {} in key {} with ttl: {}", value, key, ttl);
        try {
            redisTemplate.opsForValue().set(key, value, ttl);
        } catch (Exception e) {
            log.error("An error occurred while trying to set redis value for key {} and value {}", key, value, e);
        }
    }

    public <T> Optional<T> getValue(String key, Class<T> clazz) {
        log.info("Retrieving value for class {} in key {}", clazz, key);
        try {
            var value = redisTemplate.opsForValue().get(key);
            return Optional.ofNullable(objectMapper.convertValue(value, clazz));
        } catch (Exception e) {
            log.error("An error occurred while trying to get redis value for key {}", key);
            throw e;
        }
    }
}
