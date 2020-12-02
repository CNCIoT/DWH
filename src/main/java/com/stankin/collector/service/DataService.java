package com.stankin.collector.service;

import com.google.gson.Gson;
import com.stankin.collector.converter.DataDTOTODataConverter;
import com.stankin.collector.converter.DataTODataDTOConverter;
import com.stankin.collector.domain.Value;
import com.stankin.collector.domain.Values;
import com.stankin.collector.domain.table.Data;
import com.stankin.collector.domain.table.DataDTO;
import com.stankin.collector.mongorepository.DataMongoRepository;
import com.stankin.collector.repository.DataDTORepository;
import com.stankin.collector.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataService {

    private final DataRepository dataRepository;
    private final DataDTOTODataConverter dataDTOTODataConverter;
    private final DataDTORepository dataDTORepository;
    private final DataMongoRepository dataMongoRepository;
    private final DataTODataDTOConverter dataTODataDTOConverter;
    private final InputTypeService inputTypeService;

    @Autowired
    public DataService(DataRepository dataRepository,
                       DataDTOTODataConverter dataDTOTODataConverter,
                       DataDTORepository dataDTORepository,
                       DataMongoRepository dataMongoRepository,
                       DataTODataDTOConverter dataTODataDTOConverter, InputTypeService inputTypeService) {
        this.dataRepository = dataRepository;
        this.dataDTOTODataConverter = dataDTOTODataConverter;
        this.dataDTORepository = dataDTORepository;
        this.dataMongoRepository = dataMongoRepository;
        this.dataTODataDTOConverter = dataTODataDTOConverter;
        this.inputTypeService = inputTypeService;
    }

    /**
     * @param data
     */
    public void save(@NotNull Data data) {
        validateRequest(data);
        try {
            dataRepository.save(data.getDeviceId(), new Gson().toJson(data.getValue()), data.getCreated());
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        dataMongoRepository.save(Objects.requireNonNull(dataTODataDTOConverter.convert(data)));
    }

    private void validateRequest(@NotNull Data data) {
        Values value = data.getValue();
        Map<String, Value> valueMap = value.getValueList();
        valueMap.forEach((key, value1) -> {
            if (inputTypeService.findByKind(key) == null) {
                String str = String.format("%s не зарегистрирован в inputType", key);
                log.info(str);
                throw new IllegalArgumentException(str);
            }
        });
    }

    public List<Data> findByDeviceIdWithRows(@NotNull Long deviceId,
                                             @NotNull Long rows) {
        List<DataDTO> dataDTOList = dataDTORepository.findByDeviceIdWithRows(deviceId, rows);
        return dataDTOList.stream().map(dataDTOTODataConverter::convert).collect(Collectors.toList());
    }
}
