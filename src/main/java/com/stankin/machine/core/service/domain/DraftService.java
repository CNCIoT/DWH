package com.stankin.machine.core.service.domain;

import com.stankin.machine.core.repository.DraftRepository;
import org.springframework.stereotype.Service;

@Service
public class DraftService {

    private final DraftRepository draftRepository;

    public DraftService(DraftRepository draftRepository) {
        this.draftRepository = draftRepository;
    }
}
