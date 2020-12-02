package com.stankin.collector.service;

import com.stankin.collector.domain.table.InputTypes;
import com.stankin.collector.repository.InputTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InputTypeService {
    private final InputTypeRepository inputTypeRepository;

    @Autowired
    public InputTypeService(InputTypeRepository inputTypeRepository) {
        this.inputTypeRepository = inputTypeRepository;
    }


    public List<InputTypes> findAll() {
        return inputTypeRepository.findAll();
    }

    @Cacheable("inputTypeFindByKind")
    public InputTypes findByKind(String kind){
        return inputTypeRepository.findByKind(kind);
   }
}

