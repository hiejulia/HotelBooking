package com.project.hb.restaurant.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.project.hb.restaurant.controller.HotelController;
import com.project.hb.restaurant.domain.model.entity.Hotel;
import com.project.hb.restaurant.domain.repository.HotelRepository;
import com.project.hb.restaurant.domain.service.HotelService;
import com.project.hb.restaurant.domain.service.HotelServiceImpl;
import com.project.hb.restaurant.resources.AbstractHotelControllerTests;
import org.junit.Before;

public class HotelControllerTests extends AbstractHotelControllerTests {

    protected static final Hotel hotelStaticInstance = new Hotel(HOTEL,
            HOTEL_NAME, HOTEL_LOCATION, null);

    protected static class TestHotelRepository implements HotelRepository<Hotel, String> {

        private Map<String, Hotel> entities;

        public TestHotelRepository() {
            entities = new HashMap();
            Hotel hotel = new Hotel(HOTEL,
                    HOTEL_NAME, HOTEL_LOCATION, null);
            entities.put("1", hotel);
            hotel = new Hotel("Le Hien", "2", "Helsinki", null);
            entities.put("2", hotel);
        }

        @Override
        public boolean containsName(String name) {
            try {
                return this.findByName(name).size() > 0;
            } catch (Exception ex) {

            }
            return false;
        }

        @Override
        public void add(Hotel entity) {
            entities.put(entity.getId(), entity);
        }

        @Override
        public void remove(String id) {
            if (entities.containsKey(id)) {
                entities.remove(id);
            }
        }

        @Override
        public Collection<Hotel> findAll() {
            return null;
        }

        @Override
        public void update(Hotel entity) {
            if (entities.containsKey(entity.getId())) {
                entities.put(entity.getId(), entity);
            }
        }

        @Override
        public Collection<Hotel> findByName(String name) throws Exception {
            Collection<Hotel> hotels = new ArrayList();
            int noOfChars = name.length();
            entities.forEach((k, v) -> {
                if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                    hotels.add(v);
                }
            });
            return hotels;
        }

        @Override
        public boolean contains(String id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Hotel get(String id) {
            return entities.get(id);
        }

        @Override
        public Collection<Hotel> getAll() {
            return entities.values();
        }

        @Override
        public Collection<Hotel> findByLocation(String location) throws Exception {
            return null;
        }
    }

    /**
     * Initialized Hotel Repository
     */
    protected TestHotelRepository testHotelRepository = new TestHotelRepository();

    /**
     * Initialized Hotel Service
     */
    protected HotelService restaurantService = new HotelServiceImpl(testHotelRepository);

    /**
     * Setup method
     */
    @Before
    public void setup() {
        hotelController = new HotelController();
    }
}
