package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.Detail;
import com.stankin.machine.core.repository.DetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class DetailService {

    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public Detail save(@NotNull Detail detail) {
        log.trace(">>save... detail={}", detail);
        Optional<Detail> detailOptional = detailRepository.findById(detail.getId());
        if(detailOptional.isPresent()){
            log.trace("update existing detail");
            detailRepository.save(detail);
        }
        detail.setId(null);
        log.trace("create new detail");
        return detailRepository.save(detail);
    }
}
