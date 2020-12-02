package com.stankin.collector.service;

import com.stankin.collector.dto.NodeDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NodeServce {

    public ResponseEntity<NodeDataDTO> findByNodeId(Long nodeId) {
        return null;
    }
}
