package com.project.hb.hotel.domain.service;

import com.project.hb.hotel.domain.model.entity.Entity;
import com.project.hb.hotel.domain.model.entity.Hotel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface HotelService {

    public void add(Hotel hotel) throws Exception;

    public void update(Hotel hotel) throws Exception;

    public void delete(String id) throws Exception;

    public Entity findById(String hotelId) throws Exception;

    public Collection<Hotel> findByName(String name) throws Exception;

    public Collection<Hotel> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;

    public Collection<Hotel> getAll();
}
