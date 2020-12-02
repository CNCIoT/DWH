package com.stankin.collector.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class СacheService {
    @CacheEvict(value = "inputTypeFindByKind", allEntries = true)
    public void clear() {
        log.info("inputTypeFindByTitle cache evict");
    }
}