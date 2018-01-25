package com.project.hb.restaurant.resources;

import com.project.hb.restaurant.controller.HotelController;
import com.project.hb.restaurant.domain.model.entity.Entity;

import java.util.Collection;
import java.util.logging.Logger;

import com.project.hb.restaurant.domain.model.entity.Hotel;
import com.project.hb.restaurant.dto.HotelDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author sousharm
 */
public abstract class AbstractHotelControllerTests {

    /**
     * HOTEL ID constant having value 1
     */
    protected static final String HOTEL = "1";

    /**
     * HOTEL name constant having value Big-O Hotel
     */
    protected static final String HOTEL_NAME = "Le Meurice";

    /**
     * HOTEL LOCATION constant
     */
    protected static final String HOTEL_LOCATION = "228 rue de Rivoli, 75001, Paris";

    @Autowired
    HotelController hotelController;

    /**
     * Test method for findById method
     */
    @Test
    public void validHotelById() {
        Logger.getGlobal().info("Start validHotelById test");
        ResponseEntity<Entity> hotel = hotelController.findById(HOTEL);

        Assert.assertEquals(HttpStatus.OK, hotel.getStatusCode());
        Assert.assertTrue(hotel.hasBody());
        Assert.assertNotNull(hotel.getBody());
        Assert.assertEquals(HOTEL, hotel.getBody().getId());
        Assert.assertEquals(HOTEL_NAME, hotel.getBody().getName());
        Logger.getGlobal().info("End validHotelById test");
    }

    /**
     * Test method for findByName method
     */
    @Test
    public void validHotelByName() {
        Logger.getGlobal().info("Start validHotelByName test");
        ResponseEntity<Collection<Hotel>> hotels = hotelController.findByName(HOTEL_NAME);
        Logger.getGlobal().info("In validAccount test");

        Assert.assertEquals(HttpStatus.OK, hotels.getStatusCode());
        Assert.assertTrue(hotels.hasBody());
        Assert.assertNotNull(hotels.getBody());
        Assert.assertFalse(hotels.getBody().isEmpty());
        Hotel hotel = (Hotel) hotels.getBody().toArray()[0];
        Assert.assertEquals(HOTEL, hotel.getId());
        Assert.assertEquals(HOTEL_NAME, hotel.getName());
        Logger.getGlobal().info("End validHotelByName test");
    }

    /**
     * Test method for add method
     */
    @Test
    public void validAdd() {
        Logger.getGlobal().info("Start validAdd test");
        HotelDto hotel = new HotelDto();
        hotel.setId("999");
        hotel.setName("Test Hotel");

        ResponseEntity<Hotel> hotels = hotelController.add(hotel);
        Assert.assertEquals(HttpStatus.CREATED, hotels.getStatusCode());
        Logger.getGlobal().info("End validAdd test");
    }
}
