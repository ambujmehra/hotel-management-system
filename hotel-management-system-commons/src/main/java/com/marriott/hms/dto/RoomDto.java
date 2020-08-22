package com.marriott.hms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RoomDto {

    private Integer id;
    private RoomType roomType;
    private BigDecimal roomTariff;
    private Integer roomSize;
    private RoomStatus roomStatus;
    private Integer occupancy;
}
