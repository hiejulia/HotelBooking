package com.project.hb.restaurant.controller;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.hb.restaurant.domain.model.entity.Entity;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.project.hb.restaurant.dto.HotelDto;
import com.project.hb.restaurant.domain.model.entity.Hotel;
import com.project.hb.restaurant.domain.service.HotelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/v1/hotels")
public class HotelController {

    protected Logger logger = Logger.getLogger(HotelController.class.getName());

    @Autowired
    protected HotelService hotelService;

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/services")
    public List<String> home() {
        return client.getServices();
    }

    /**
     * Fetch hotels with the specified name. A partial case-insensitive
     * match is supported. So <code>http://.../hotels/rest</code> will find
     * any hotels with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of hotels.
     */
    @HystrixCommand(fallbackMethod = "defaultHotels")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Hotel>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("hotel-service findByName() invoked:{} for {} ", hotelService.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<Hotel> hotels;
        try {
            hotels = hotelService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return hotels.size() > 0 ? new ResponseEntity<>(hotels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<Collection<Hotel>> findAll() {

        Collection<Hotel> hotels;
        try {
            hotels = hotelService.getAll();
        } catch (Exception ex) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(hotels, HttpStatus.OK);

    }




    /**
     * Fetch hotels with the given id.
     * <code>http://.../v1/hotels/{hotel_id}</code> will return
     * hotel with given id.
     *
     * @param id
     * @return A non-null, non-empty collection of hotels.
     */
    @HystrixCommand(fallbackMethod = "defaultHotel")
    @RequestMapping(value = "/{hotel_id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("hotel_id") String id) {
        logger.info(String.format("hotel-service findById() invoked:{} for {} ", hotelService.getClass().getName(), id));
        id = id.trim();
        Entity hotel;
        try {
            hotel = hotelService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return hotel != null ? new ResponseEntity<>(hotel, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add hotel with the specified information.
     *
     * @param hotelDto
     * @return A non-null hotel.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Hotel> add(@RequestBody HotelDto hotelDto) {
        logger.info(String.format("hotel-service add() invoked: %s for %s", hotelService.getClass().getName(), hotelDto.getName()));

        Hotel hotel = new Hotel(null, null, null, null);
        BeanUtils.copyProperties(hotelDto, hotel);
        try {
            hotelService.add(hotel);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Hotel REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<Entity> defaultHotel(String input) {
        logger.warning("Fallback method for hotel-service is being used.");
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<Collection<Hotel>> defaultHotels(String input) {
        logger.warning("Fallback method for user-service is being used.");
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
