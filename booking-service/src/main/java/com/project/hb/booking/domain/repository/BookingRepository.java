package com.project.hb.booking.domain.repository;

import java.awt.print.Book;
import java.nio.charset.CodingErrorAction;
import java.util.Collection;

/**
 * BOOKING REPOSITORY
 * @param <Booking>
 * @param <String>
 */
public interface BookingRepository<Booking, String> extends Repository<Booking, String> {


    boolean containsName(String name);


    public Collection<Booking> findByName(String name) throws Exception;



    public Collection<Booking> findByUserId(String id) throws Exception;

    public Collection<Booking> findByHotelId(String id) throws Exception;

    public Collection<Booking> findByRoomId(String id) throws Exception;
}
