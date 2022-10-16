package com.linmsen.td.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class KeyData {
    private Timestamp time;
    private Long deviceId;
    private Long stationId;
    private Long companyId;
    private Double pvElec;
    private Double elec;
    private Double onGrid;
    private Double offGrid;
    private Double charge;
    private Double disCharge;
    private Double consume;
}
