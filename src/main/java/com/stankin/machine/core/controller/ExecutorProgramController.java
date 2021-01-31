package com.stankin.machine.core.controller;

import com.stankin.machine.core.domain.ExecutorProgram;
import com.stankin.machine.core.service.domain.ExecutorProgramService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/executor/program")
public class ExecutorProgramController {

    private final ExecutorProgramService executorProgramService;

    public ExecutorProgramController(ExecutorProgramService executorProgramService) {
        this.executorProgramService = executorProgramService;
    }

    @PostMapping
    @Operation(summary = "сохранение сущности")
    public ResponseEntity<ExecutorProgram> save(@NotNull @RequestBody ExecutorProgram executorProgram){
        return ResponseEntity.ok(executorProgramService.save(executorProgram));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExecutorProgram> findById(@PathVariable("id") Long id){
       Optional<ExecutorProgram> optionalExecutorProgram = executorProgramService.findById(id);
        return optionalExecutorProgram.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestBody ExecutorProgram executorProgram){
        executorProgramService.delete(executorProgram);
        return ResponseEntity.noContent().build();
    }

}
