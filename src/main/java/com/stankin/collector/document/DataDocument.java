package com.stankin.collector.document;

import com.stankin.collector.domain.Values;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "values")
@Data
@Builder
public class DataDocument {
    @Id
    private String id;
    private Long deviceId;
    private Values value;
    private Date created = new Date();
}
