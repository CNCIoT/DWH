package com.stankin.collector.converter;

import com.stankin.collector.document.DataDocument;
import com.stankin.collector.domain.table.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DataTODataDTOConverter implements Converter<Data, DataDocument> {
    @Override
    public DataDocument convert(Data data) {
        return DataDocument
                .builder()
                .deviceId(data.getDeviceId())
                .value(data.getValue())
                .created(data.getCreated())
                .build();
    }
}