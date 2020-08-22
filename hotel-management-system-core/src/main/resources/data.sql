DROP TABLE IF EXISTS hotels;
DROP TABLE IF EXISTS rooms;

CREATE TABLE hotels (
    id INTEGER AUTO_INCREMENT  PRIMARY KEY,
    hotel_name VARCHAR(255) UNIQUE NOT NULL ,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(64) NOT NULL,
    pincode VARCHAR(64) NOT NULL,
    rating DECIMAL NOT NULL,
    created_at DATE DEFAULT NOW(),
    updated_at DATE DEFAULT NOW()
);

CREATE TABLE rooms (
    id INTEGER AUTO_INCREMENT  PRIMARY KEY,
    room_type VARCHAR(64) NOT NULL,
    room_tariff DECIMAL(18,2) NOT NULL,
    occupancy INTEGER DEFAULT 1,
    room_status VARCHAR(64) NOT NULL,
    hotel_id INTEGER,
    created_at DATE DEFAULT NOW(),
    updated_at DATE DEFAULT NOW()
);

ALTER TABLE rooms add FOREIGN KEY(hotel_id) REFERENCES hotels(id);