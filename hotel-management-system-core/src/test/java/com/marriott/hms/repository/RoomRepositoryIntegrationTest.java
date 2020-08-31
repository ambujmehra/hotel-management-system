package com.marriott.hms.repository;

import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryIntegrationTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testAllInjectsNotNull() {
        Assert.assertNotNull(roomRepository);
    }

    @Test
    public void testfindByHotelAndRoomType() {
        Hotel expectedHotel = hotelRepository.findByHotelName("Marriott rosymary");
        Assert.assertEquals(expectedHotel.getHotelName(), "Marriott rosymary");
        List<Room> expectedRoomDtos = roomRepository.findByHotelAndRoomType(expectedHotel, RoomType.SINGLE_OCCUPANCY_ROOM);
        Assert.assertEquals(expectedRoomDtos.size(), 1);
    }

    @Test
    public void testfindByHotelAndRoomStatus() {
        Hotel expectedHotel = hotelRepository.findByHotelName("Marriott rosymary");
        Assert.assertEquals(expectedHotel.getHotelName(), "Marriott rosymary");
        List<Room> expectedRoomDtos = roomRepository.findByHotelAndRoomStatus(expectedHotel, RoomStatus.EMPTY);
        Assert.assertEquals(expectedRoomDtos.size(), 2);
    }

    @Test
    public void testfindByHotelAndRoomStatusAndRoomType() {
        Hotel expectedHotel = hotelRepository.findByHotelName("Marriott rosymary");
        Assert.assertEquals(expectedHotel.getHotelName(), "Marriott rosymary");
        List<Room> expectedRoomDtos = roomRepository.findByHotelAndRoomStatusAndRoomType(expectedHotel, RoomStatus.EMPTY, RoomType.SINGLE_OCCUPANCY_ROOM);
        Assert.assertEquals(expectedRoomDtos.size(), 1);
    }

    @Test
    public void testFindByHotelAndRoomId() {
        Hotel expectedHotel = hotelRepository.findByHotelName("Marriott rosymary");
        Assert.assertEquals(expectedHotel.getHotelName(), "Marriott rosymary");
        Room room = roomRepository.findByHotelAndId(expectedHotel, 2);
        Assert.assertEquals(room.getRoomType(), RoomType.DOUBLE_OCCUPANCY_ROOM);
    }

}
