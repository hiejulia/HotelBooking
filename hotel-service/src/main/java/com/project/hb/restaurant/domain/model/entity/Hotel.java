package com.project.hb.restaurant.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends BaseEntity<String> {

    private List<Room> rooms = new ArrayList<>();
    private String location;
    public Hotel(String name, String id, String location, List<Room> rooms) {
        super(id, name);
        this.location = location;
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "rooms=" + rooms +
                ", location='" + location + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
