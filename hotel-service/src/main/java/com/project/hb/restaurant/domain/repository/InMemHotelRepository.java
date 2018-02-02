package com.project.hb.hotel.domain.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.project.hb.hotel.domain.model.entity.Hotel;
import org.springframework.stereotype.Repository;

@Repository("hotelRepository")
public class InMemHotelRepository implements HotelRepository<Hotel, String> {

    private Map<String, Hotel> entities;

    /**
     * Initialize the in-memory hotel Repository with empty Map
     */
    public InMemHotelRepository() {
        entities = new HashMap();
        //public Hotel(String name, String id, String location, List<Room> rooms) {
        Hotel hotel = new Hotel("Le Meurice","1","Paris",null);
        entities.put("1", hotel);
        hotel = new Hotel("L'Ambroisie","2","Helsinki",null);
        entities.put("2", hotel);
        hotel = new Hotel("Arpège","3","Praha",null);
        entities.put("3", hotel);
        hotel = new Hotel("Alain Ducasse au Plaza Athénée","4","Zurich",null);
        entities.put("4", hotel);
        hotel = new Hotel("Pavillon LeDoyen","5","Berlin",null);
        entities.put("5", hotel);
        hotel = new Hotel("Pierre Gagnaire","6","Frankfurt",null);
        entities.put("6", hotel);
        hotel = new Hotel("Guy Savoy","7","NewYork",null);
        entities.put("7", hotel);
        hotel = new Hotel("Pré Catelan","8","Chicago",null);
        entities.put("8", hotel);
        hotel = new Hotel("L'Astrance","9","HoChiMinh",null);
        entities.put("9", hotel);
        hotel = new Hotel("Le Bristol","10","California",null);
        entities.put("10", hotel);
    }

    @Override
    public Collection<Hotel> findAll() {
        return entities.values();
    }

    @Override
    public boolean containsName(String name) {
        try {
            return this.findByName(name).size() > 0;
        } catch (Exception ex) {
            //Exception Handler
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
    public void update(Hotel entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
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
    public Collection<Hotel> findByName(String name) throws Exception {
        Collection<Hotel> hotels = new ArrayList();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase().subSequence(0, noOfChars))) {
                hotels.add(v);
            }
        });
        return hotels;
    }

    @Override
    public Collection<Hotel> findByLocation(String location) throws Exception {
        Collection<Hotel> hotels = new ArrayList<>();
        entities.forEach((k,v) -> {
            if(v.getLocation().trim().equals(location.trim())){
                hotels.add(v);

            }

        });
        return hotels;
    }
}
