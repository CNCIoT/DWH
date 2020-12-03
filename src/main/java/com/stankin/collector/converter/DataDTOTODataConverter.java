package com.stankin.collector.converter;

import com.google.gson.Gson;
import com.stankin.collector.domain.Values;
import com.stankin.collector.domain.table.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataDTOTODataConverter implements Converter<Data, com.stankin.collector.domain.document.Data> {
    @Override
    public com.stankin.collector.domain.document.Data convert(Data dataRDBM) {
        Gson gson = new Gson();
        com.stankin.collector.domain.document.Data data = new com.stankin.collector.domain.document.Data();
        log.info(dataRDBM.getValue());
        data.setValue(gson.fromJson(dataRDBM.getValue(), Values.class));
        data.setCreated(dataRDBM.getCreated());
        data.setDeviceId(dataRDBM.getDeviceId());
        data.setId(dataRDBM.getId());
        return data;
    }
}
