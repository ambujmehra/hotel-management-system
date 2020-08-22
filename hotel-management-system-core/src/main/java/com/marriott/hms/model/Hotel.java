package com.marriott.hms.model;

import com.marriott.hms.model.base.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
public class Hotel extends BaseModel {

    @Column(name = "hotel_name", nullable = false, unique = true)
    private String hotelName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "pincode", nullable = false)
    private String pinCode;

    @Column(name = "rating", nullable = false)
    private BigDecimal rating;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

}
