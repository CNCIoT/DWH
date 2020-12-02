package com.stankin.collector.domain.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("machine_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeWork {
    @Column("work_time")
    private String workTime;
    @Column("chan1_status")
    private String chan1Status;
}
