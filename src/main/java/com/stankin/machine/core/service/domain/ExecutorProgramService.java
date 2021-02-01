package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.domain.ExecutorProgram;
import com.stankin.machine.core.repository.ExecutorProgramRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
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
        if (executorProgram.getCreatedAt() == null) {
            executorProgram.setCreatedAt(new Date());
        }
        if (executorProgram.getUpdatedAt() == null) {
            executorProgram.setUpdatedAt(new Date());
        }
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

    public List<ExecutorProgram> findAllByFilter(Long employeeId,
                                                 String fileName,
                                                 Date startDate,
                                                 Date endDate) {
        log.trace(">>findAllByFilter... operatorFullName={}, fileName={}, startDate={}, endDate={}",
                employeeId, fileName, startDate, endDate);
        return executorProgramRepository.findAllByFilter(employeeId, fileName, startDate, endDate);
    }


    public int findAllWithGroupByEmpAndFileName(Long employeeId, String fileName) {
        log.trace("findAllWithGroupByEmpAndFileName... employeeId={}, fileName={}", employeeId, fileName);
        return executorProgramRepository.findAllWithGroupByEmpAndFileName(employeeId, fileName);
    }

    public double calculateActualMachineTime(Long employeeId,
                                          String fileName,
                                          Date startDate,
                                          Date endDate) {
        log.trace(">>calculateActualMachineTime... employeeId={}, fileName={}, startDate={}, endDate={}", employeeId, fileName, startDate, endDate);
        return executorProgramRepository.calculateActualMachineTime(employeeId, fileName, startDate, endDate);
    }

    public List<ExecutorProgram> findAllByStartAndEndDate(Date startDate, Date endDate){
        log.trace("findAllByStartAndEndDate... startDate={}, endDate={}", startDate, endDate);
        return executorProgramRepository.findAllByStartAndEndDate(startDate, endDate);
    }





}
