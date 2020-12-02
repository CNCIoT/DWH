package com.stankin.collector.converter;

import com.google.gson.Gson;
import com.stankin.collector.domain.Values;
import com.stankin.collector.domain.table.Data;
import com.stankin.collector.domain.table.DataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataDTOTODataConverter implements Converter<DataDTO, Data> {
    @Override
    public Data convert(DataDTO dataDTO) {
        Gson gson = new Gson();
        Data data = new Data();
        log.info(dataDTO.getValue());
        data.setValue(gson.fromJson(dataDTO.getValue(), Values.class));
        data.setCreated(dataDTO.getCreated());
        data.setDeviceId(dataDTO.getDeviceId());
        data.setId(dataDTO.getId());
        return data;
    }
}
