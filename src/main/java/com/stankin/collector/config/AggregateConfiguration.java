package com.stankin.collector.config;

import com.stankin.collector.domain.document.Data;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class AggregateConfiguration extends AbstractJdbcConfiguration {
    final AtomicInteger id = new AtomicInteger(0);

    @Bean
    public ApplicationListener<?> idSetting() {
        return (ApplicationListener<BeforeSaveEvent>) event -> {

            if (event.getEntity() instanceof Data) {
                setIds((Data) event.getEntity());
            }
        };
    }

    private void setIds(Data data) {

        if (data.getId() == 0) {
            data.setId((long) id.incrementAndGet());
        }
    }
}
