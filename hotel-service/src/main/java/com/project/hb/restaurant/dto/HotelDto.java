package com.project.hb.hotel.dto;

import com.project.hb.hotel.domain.model.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class HotelDto {

    private List<Room> rooms = new ArrayList<>();
    private String name;
    private String id;
    private String location;

    public HotelDto(List<Room> rooms, String name, String id, String location) {
        this.rooms = rooms;
        this.name = name;
        this.id = id;
        this.location = location;
    }

    public HotelDto(){}

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "HotelDto{" +
                "rooms=" + rooms +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
