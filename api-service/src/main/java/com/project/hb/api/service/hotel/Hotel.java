package com.project.hb.api.service.hotel;

import java.util.ArrayList;
import java.util.List;

class Hotel {

    private List<Room> rooms = new ArrayList<>();
    private String id;
    private boolean isModified;
    private String name;
    private String location;

    public Hotel(List<Room> rooms, String id, boolean isModified, String name, String location) {
        this.rooms = rooms;
        this.id = id;
        this.isModified = isModified;
        this.name = name;
        this.location = location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", id='" + id + '\'' +
                ", isModified=" + isModified +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}