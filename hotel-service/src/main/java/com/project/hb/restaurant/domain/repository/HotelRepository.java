package com.project.hb.hotel.domain.repository;

import java.util.Collection;

public interface HotelRepository<Hotel, String> extends Repository<Hotel, String> {

    boolean containsName(String name);

    public Collection<Hotel> findByName(String name) throws Exception;

    public Collection<Hotel> findByLocation(String location) throws Exception;

    public Collection<Hotel> findAll();
}
