package com.marriott.hms.repository;

import com.marriott.hms.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {

    Hotel findByHotelName(String hotelName);

}
