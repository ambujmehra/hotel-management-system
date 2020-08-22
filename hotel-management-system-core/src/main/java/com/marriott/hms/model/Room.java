package com.marriott.hms.model;

import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.model.base.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room extends BaseModel {

    @Column(name = "room_type", nullable = false)
    @Enumerated
    private RoomType roomType;

    @Column(name = "room_tariff", nullable = false)
    private BigDecimal roomTariff;

    @Column(name = "room_size", nullable = false)
    private Integer roomSize;

    @Column(name = "occupancy", nullable = false, columnDefinition = "integer default 1")
    private Integer occupancy;

    @Column(name = "room_status", nullable = false)
    @Enumerated
    private RoomStatus roomStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

}
