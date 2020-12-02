package com.stankin.collector.controller;

import com.stankin.collector.dto.NodeDataDTO;
import com.stankin.collector.service.NodeServce;
import com.stankin.collector.utils.RestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/node/data")
public class NodeController {

    private final NodeServce nodeServce;

    @Autowired
    public NodeController(NodeServce nodeServce) {
        this.nodeServce = nodeServce;
    }

    @GetMapping("/findByNodeId")
    @Operation(summary = "получение данных девайсов по нодам", description = "", responses = {
            @ApiResponse(responseCode = RestUtil.HTTP_OK,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NodeDataDTO.class))),
            @ApiResponse(responseCode = RestUtil.BAD_GATEWAY,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Ошибка при получении данных")
    })
    public ResponseEntity<NodeDataDTO> findByNodeId(@RequestParam("nodeID") Long nodeId){
        return nodeServce.findByNodeId(nodeId);
    }

}
