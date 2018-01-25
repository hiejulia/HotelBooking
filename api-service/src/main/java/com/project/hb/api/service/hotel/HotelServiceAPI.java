package com.project.hb.api.service.hotel;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@RestController
public class HotelServiceAPI {

    private static final Logger LOG = LoggerFactory.getLogger(HotelServiceAPI.class);

    //@Qualifier("userInfoRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/service")
    public List<String> home() {
        return client.getServices();
    }

    @RequestMapping("/hotels/{hotel-id}")
    @HystrixCommand(fallbackMethod = "defaultHotel")
    public ResponseEntity<Hotel> getHotel(
            @PathVariable("hotel-id") int id) {
        MDC.put("hotelId", id);
        String url = "http://hotel-service/v1/hotels/" + id;


        ResponseEntity<Hotel> result = restTemplate.getForEntity(url, Hotel.class);
        LOG.info("{}", result.getStatusCode());
        LOG.debug("{}", result.getBody());

        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }


    @HystrixCommand(fallbackMethod = "defaultHotels")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Hotel>> findByName(@RequestParam("name") String name) {

        MDC.put("hotelId", name);
        String url = "http://hotel-service/v1/hotels?name=".concat(name);

        Collection<Hotel> hotels;
        ResponseEntity<Collection> result = restTemplate.getForEntity(url, Collection.class);
        LOG.info("", result.getStatusCode());
        LOG.debug("", result.getBody());

        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }

    /**
     * Fallback method for getHotel()
     *
     * @param hotelId
     * @return
     */
    public ResponseEntity<Hotel> defaultHotel(
            @PathVariable int hotelId) {
        return new ResponseEntity<>( HttpStatus.BAD_GATEWAY);
    }

    /**
     * Fallback method for findByName()
     *
     * @param input
     * @return
     */
    public ResponseEntity<Collection<Hotel>> defaultHotels(String input) {
        LOG.warn("Fallback method for Hotel-service is being used.");
        LOG.info("Fallback method for Hotel service is invoked");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}