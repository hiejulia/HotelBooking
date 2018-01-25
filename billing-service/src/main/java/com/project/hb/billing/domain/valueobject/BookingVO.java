package com.project.hb.billing.domain.valueobject;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingVO {

    private String name;
    private String id;
    private String hotelId;
    private String userId;
    private LocalDate date;

    private LocalTime time;
    private String roomId;

    public BookingVO(String name, String id, String hotelId, String userId, LocalDate date, LocalTime time, String roomId) {
        this.name = name;
        this.id = id;
        this.hotelId = hotelId;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.roomId = roomId;
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

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "BookingVO{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
