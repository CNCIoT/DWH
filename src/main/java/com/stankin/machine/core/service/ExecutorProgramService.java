package com.stankin.machine.core.service;

import com.stankin.machine.core.domain.ExecutorProgram;
import com.stankin.machine.core.repository.ExecutorProgramRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
public class ExecutorProgramService {

    private final ExecutorProgramRepository executorProgramRepository;

    public ExecutorProgramService(ExecutorProgramRepository executorProgramRepository) {
        this.executorProgramRepository = executorProgramRepository;
    }

    public ExecutorProgram save(@NotNull ExecutorProgram executorProgram) {
        log.trace(">>save... executorProgram={}", executorProgram);
        Optional<ExecutorProgram> executorProgramOptional = executorProgramRepository.findById(executorProgram.getId());
        if (executorProgramOptional.isPresent()) {
            log.trace("update existing executorProgram");
            return executorProgramRepository.save(executorProgram);
        }
        executorProgram.setId(null);
        log.trace("create new executorProgram");
        return executorProgramRepository.save(executorProgram);
    }

    public Optional<ExecutorProgram> findById(Long id) {
        log.trace(">>findById... id={}", id);
        return executorProgramRepository.findById(id);
    }

    public void delete(ExecutorProgram executorProgram) {
        log.trace(">>delete... executorProgram={}", executorProgram);
        executorProgramRepository.delete(executorProgram);
    }
}
